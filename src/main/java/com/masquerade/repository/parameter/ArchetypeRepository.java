package com.masquerade.repository.parameter;

import com.masquerade.model.entity.parameter.ArchetypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchetypeRepository extends JpaRepository<ArchetypeEntity, Long> {
}