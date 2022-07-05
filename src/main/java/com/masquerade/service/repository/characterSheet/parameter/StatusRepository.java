package com.masquerade.service.repository.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
}
