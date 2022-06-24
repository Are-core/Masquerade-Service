package com.masquerade.repository.parameter;

import com.masquerade.model.entity.parameter.SectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectRepository extends JpaRepository<SectEntity, Long> {
}
