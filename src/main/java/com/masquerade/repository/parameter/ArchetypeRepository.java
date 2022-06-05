package com.masquerade.repository.parameter;

import com.masquerade.model.parameter.ArchetypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchetypeRepository extends JpaRepository<ArchetypeEntity, Long> {
    @Query("SELECT a FROM ArchetypeEntity a WHERE a.description_EN LIKE  %:searchText%")
    List<ArchetypeEntity> findByDescription(@Param("searchText") String searchText);
}