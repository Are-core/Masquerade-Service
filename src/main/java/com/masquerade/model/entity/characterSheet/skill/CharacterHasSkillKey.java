package com.masquerade.model.entity.characterSheet.skill;

import jakarta.persistence.*;
import java.io.Serializable;

@Embeddable
public class CharacterHasSkillKey implements Serializable {
    @Column(name = "character_id")
    public Long characterId;

    @Column(name = "skill_id")
    public Long skillId;

    public CharacterHasSkillKey() {
    }

    public CharacterHasSkillKey(Long characterId, Long skillId) {
        this.characterId = characterId;
        this.skillId = skillId;
    }
}
