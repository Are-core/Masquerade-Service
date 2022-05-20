package com.masquerade.repository;

import com.masquerade.model.ArchetypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchetypeRepository extends JpaRepository<ArchetypeEntity, Long> {
    @Query("SELECT a FROM ArchetypeEntity a WHERE a.description LIKE  %:description%")
    List<ArchetypeEntity> findByDescription(@Param("description") String name);
}