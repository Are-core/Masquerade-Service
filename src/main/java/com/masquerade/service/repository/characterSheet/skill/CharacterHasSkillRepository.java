package com.masquerade.service.repository.characterSheet.skill;

import com.masquerade.model.entity.characterSheet.skill.CharacterHasSkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterHasSkillRepository extends JpaRepository<CharacterHasSkillEntity, Long> {
    List<CharacterHasSkillEntity> findByCharacterId(Long id);
    CharacterHasSkillEntity findByCharacterIdAndSkillId(Long characterId, Long skillId);
    Boolean existsByCharacterIdAndSkillId(Long characterId, Long skillId);
}
