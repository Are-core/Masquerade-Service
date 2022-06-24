package com.masquerade.repository.parameter;

import com.masquerade.model.entity.parameter.StatusTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTypeRepository extends JpaRepository<StatusTypeEntity, Long> {
}
