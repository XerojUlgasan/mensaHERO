package com.mensahero.mensahero.repository;

import com.mensahero.mensahero.DTO.dashboard.DashboardCount;
import com.mensahero.mensahero.model.Message;
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

    List<Message> findByApiIdAndReceiver(@Param("apiId") UUID apiId,
                                         @Param("receiver") String recipient,
                                         Pageable pageable);

    ///////////////////////////////////// HELPER FOR OTHER SERVICES

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
