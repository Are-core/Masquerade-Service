package com.masquerade.repository.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.global.SectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectRepository extends JpaRepository<SectEntity, Long> {
}
