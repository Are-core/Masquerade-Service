package com.masquerade.repository;

import com.masquerade.model.SmallEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmallEntityRepository extends JpaRepository<SmallEntity, Long> {
}
