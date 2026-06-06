package com.mensahero.mensahero.repository;

import com.mensahero.mensahero.model.Key;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KeyRepository extends JpaRepository<Key, UUID> {
    List<Key> findByOwnerId(@Param("ownerId") UUID ownerId);
    Optional<Key> findByKey(@Param("key") String key);
}
