package com.masquerade.model.entity.characterSheet.parameter;

import javax.persistence.Column;
import java.io.Serializable;

public class CharacterHasStatusKey implements Serializable {
    @Column(name = "character_id")
    Long characterId;

    @Column(name = "status_id")
    Long statusId;

    public CharacterHasStatusKey() {
    }

    public CharacterHasStatusKey(Long characterId, Long statusId) {
        this.characterId = characterId;
        this.statusId = statusId;
    }
}
