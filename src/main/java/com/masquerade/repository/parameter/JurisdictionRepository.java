package com.masquerade.repository.parameter;

import com.masquerade.model.parameter.JurisdictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JurisdictionRepository extends JpaRepository<JurisdictionEntity, Long> {
    @Query("SELECT a FROM JurisdictionEntity a WHERE a.description LIKE  %:searchText%")
    List<JurisdictionEntity> findByDescription(@Param("searchText") String searchText);
}
