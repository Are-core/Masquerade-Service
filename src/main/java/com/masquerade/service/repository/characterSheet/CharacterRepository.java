package com.masquerade.service.repository.characterSheet;

import com.masquerade.model.entity.characterSheet.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}

