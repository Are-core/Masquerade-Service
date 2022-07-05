package com.masquerade.service.repository.characterSheet.skill;

import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillSpecializationRepository extends JpaRepository<SkillSpecializationEntity, Long> {
}
