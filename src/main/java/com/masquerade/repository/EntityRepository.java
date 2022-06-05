package com.masquerade.repository;

import com.masquerade.model.EntityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntityRepository extends JpaRepository<EntityEntity, Long> {
    @Query("SELECT id, name FROM EntityEntity")
    List<EntityEntity> findEntityList();
}
