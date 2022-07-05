package com.masquerade.service.repository.characterSheet.skill;

import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {
}
