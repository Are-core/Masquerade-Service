package com.masquerade.model.entity.characterSheet.parameter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.masquerade.model.entity.characterSheet.CharacterEntity;

import jakarta.persistence.*;

@Table(name="character_has_status")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class CharacterHasStatusEntity {
    @EmbeddedId
    private CharacterHasStatusKey id;

    @ManyToOne
    //@MapsId("character")
    @JoinColumn(name = "character_id", insertable = false, updatable = false)
    private CharacterEntity character;

    @ManyToOne
    //@MapsId("status")
    @JoinColumn(name = "status_id", insertable = false, updatable = false)
    private StatusEntity status;

    @Column(name = "entity_id")
    private Long entityId;

    public CharacterHasStatusEntity() {
    }

    public CharacterHasStatusEntity(Long characterId, Long statusId, Long entityId) {
        this.id = new CharacterHasStatusKey(characterId, statusId);
        this.entityId = entityId;
    }

    public CharacterHasStatusEntity(CharacterHasStatusKey id, Long entityId) {
        this.id = id;
        this.entityId = entityId;
    }

    public CharacterHasStatusKey getId() {
        return id;
    }

    public void setId(CharacterHasStatusKey id) {
        this.id = id;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }

    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }
}
