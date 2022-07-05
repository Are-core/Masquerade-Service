package com.masquerade.repository.characterSheet.global;

import com.masquerade.model.entity.characterSheet.global.ClanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClanRepository extends JpaRepository<ClanEntity, Long> {
}
