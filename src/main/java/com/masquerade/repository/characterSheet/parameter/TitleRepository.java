package com.masquerade.repository.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.global.TitleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRepository extends JpaRepository<TitleEntity, Long> {
}
