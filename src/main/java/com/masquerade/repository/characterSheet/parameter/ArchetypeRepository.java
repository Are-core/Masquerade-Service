package com.masquerade.repository.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchetypeRepository extends JpaRepository<ArchetypeEntity, Long> {
}