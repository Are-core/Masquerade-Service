package com.masquerade.repository.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.parameter.CharacterHasStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacterHasStatusRepository extends JpaRepository<CharacterHasStatusEntity, Long> {
    List<CharacterHasStatusEntity> findByCharacterId(Long id);
    CharacterHasStatusEntity findByCharacterIdAndStatusId(Long characterId, Long statusId);
    Boolean existsByCharacterIdAndStatusId(Long characterId, Long statusId);
}
