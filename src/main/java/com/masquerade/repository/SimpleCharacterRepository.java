package com.masquerade.repository;

import com.masquerade.model.SimpleCharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimpleCharacterRepository extends JpaRepository<SimpleCharacterEntity, Long> {
}
