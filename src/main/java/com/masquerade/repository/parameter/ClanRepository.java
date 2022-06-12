package com.masquerade.repository.parameter;

import com.masquerade.model.parameter.ArchetypeEntity;
import com.masquerade.model.parameter.ClanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClanRepository extends JpaRepository<ClanEntity, Long> {
}
