package com.mensahero.mensahero.repository;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.Enums.DateFilters;
import com.mensahero.mensahero.Enums.MessageStatus;
import com.mensahero.mensahero.model.Message;
import com.mensahero.mensahero.projections.messages.MessageCounterProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {

    @Query(value = """
    SELECT *
    FROM (
        SELECT DISTINCT ON (receiver) *
        FROM public."Messages"
        WHERE api_id = :apiId
        ORDER BY receiver DESC
        LIMIT :limit OFFSET :offset
    ) t
    ORDER BY created_at DESC;
    """, nativeQuery = true)
    List<Message> findByApiId(@Param("apiId") UUID apiId,
                              @Param("limit") int limit,
                              @Param("offset") int offset);

    @Query(value = """
        SELECT *
        FROM public."Messages"
        WHERE api_id = :apiId
          AND receiver = :receiver
          AND (
                :status = 'all'
                OR status = :status
              )
    """, nativeQuery = true)
    List<Message> findByApiIdAndReceiverAndStatus(@Param("apiId") UUID apiId,
                                         @Param("receiver") String recipient,
                                         @Param("status") String status,
                                         Pageable pageable);

    ///////////////////////////////////// HELPER FOR OTHER SERVICES

    @Query(value = """
        SELECT
            COUNT(id) AS totalMessages,
            COUNT(id) FILTER (WHERE status <> 'delivered') AS failedMessages
        FROM public."Messages"
        WHERE api_id IN (:apiIds);
    """, nativeQuery = true)
    MessageCounterProjection getDashboardCounters(@Param("apiIds") List<UUID> apiId);

    @Query(value = """
        WITH params AS (
            SELECT
                CAST(:filter AS text) AS filter
        ),
        
        time_buckets AS (
        
            SELECT generate_series(
                CASE
                    WHEN (SELECT filter FROM params) = 'DAILY'
                        THEN date_trunc('day', now())
                    WHEN (SELECT filter FROM params) = 'WEEKLY'
                        THEN date_trunc('week', now())
                    WHEN (SELECT filter FROM params) = 'MONTHLY'
                        THEN date_trunc('month', now())
                    WHEN (SELECT filter FROM params) = 'YEARLY'
                        THEN date_trunc('year', now())
                END,
        
                CASE
                    WHEN (SELECT filter FROM params) = 'DAILY'
                        THEN date_trunc('day', now()) + interval '23 hours'
                    WHEN (SELECT filter FROM params) = 'WEEKLY'
                        THEN date_trunc('week', now()) + interval '6 days'
                    WHEN (SELECT filter FROM params) = 'MONTHLY'
                        THEN date_trunc('month', now()) + interval '1 month'
                    WHEN (SELECT filter FROM params) = 'YEARLY'
                        THEN date_trunc('year', now()) + interval '11 months'
                END,
        
                CASE
                    WHEN (SELECT filter FROM params) = 'DAILY'
                        THEN interval '1 hour'
                    WHEN (SELECT filter FROM params) = 'WEEKLY'
                        THEN interval '1 day'
                    WHEN (SELECT filter FROM params) = 'MONTHLY'
                        THEN interval '3 days'
                    WHEN (SELECT filter FROM params) = 'YEARLY'
                        THEN interval '1 month'
                END
            ) AS bucket
        ),
        
        messages_grouped AS (
            SELECT
                CASE
                    WHEN :filter = 'DAILY' THEN date_trunc('hour', created_at)
                    WHEN :filter = 'WEEKLY' THEN date_trunc('day', created_at)
                    WHEN :filter = 'MONTHLY' THEN
                        date_trunc('day', created_at)
                        - ((extract(day from created_at)::int - 1) % 3) * interval '1 day'
                    WHEN :filter = 'YEARLY' THEN date_trunc('month', created_at)
                END AS bucket,
        
                COUNT(*) AS count
            FROM "Messages"
            WHERE api_id IN (:apiIds)
              AND created_at >=
                CASE
                    WHEN :filter = 'DAILY' THEN date_trunc('day', now())
                    WHEN :filter = 'WEEKLY' THEN date_trunc('week', now())
                    WHEN :filter = 'MONTHLY' THEN date_trunc('month', now())
                    WHEN :filter = 'YEARLY' THEN date_trunc('year', now())
                END
            GROUP BY bucket
        )
        
        SELECT
            t.bucket AS date,
            COALESCE(m.count, 0) AS count
        FROM time_buckets t
        LEFT JOIN messages_grouped m
            ON m.bucket = t.bucket
        ORDER BY t.bucket DESC;
    """, nativeQuery = true)
    List<Object[]> getMessagesOverTime(
            @Param("apiIds") List<UUID> apiIds,
            @Param("filter") String filter
    );

    @Query(value = """
        SELECT api_id, count(id)
        FROM public."Messages"
        WHERE api_id in (:apiIds)
        GROUP BY api_id
    """, nativeQuery = true)
    List<Object[]> countByApiIds(@Param("apiIds") List<UUID> apiIds);

    @Query(value = """
            SELECT * FROM public."Messages"
            WHERE api_id IN (:apiIds)
        """, nativeQuery = true)
    List<Message> findByApiIds(@Param("apiIds") List<UUID> apiIds, Pageable pageable);
}
