package com.masquerade.repository.parameter;

import com.masquerade.model.parameter.ArchetypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchetypeRepository extends JpaRepository<ArchetypeEntity, Long> {
}