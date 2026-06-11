package com.mensahero.mensahero.repository;

import com.mensahero.mensahero.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KeyRepository extends JpaRepository<Key, UUID> {
    @Query(value = """
            SELECT id
            FROM public."Keys" m
            WHERE m.owner_id = :ownerId
    """, nativeQuery = true)
    List<UUID> getKeysId(@Param("ownerId") UUID ownerId);

    List<Key> findByOwnerId(@Param("ownerId") UUID ownerId);

    Optional<Key> findByIdAndOwnerId(@Param("id") UUID keyId, @Param("ownerId") UUID ownerId );

    Optional<Key> findByOwnerIdAndId(@Param("ownerId") UUID ownerId, @Param("id") UUID key);

    Optional<Key> findByKey(@Param("key") String key);
}
