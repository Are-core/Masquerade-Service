package com.masquerade.repository;

import com.masquerade.model.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {
}

