package com.masquerade.model.entity.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.CharacterEntity;
import com.masquerade.model.entity.characterSheet.skill.SkillEntity;

import javax.persistence.*;

@Table(name="character_has_status")
@Entity
public class CharacterHasStatusEntity {
    @EmbeddedId
    private CharacterHasStatusKey id;

    @ManyToOne
    @MapsId("character")
    @JoinColumn(name = "character_id")
    private CharacterEntity character;

    @ManyToOne
    @MapsId("status")
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    @Column(name = "entity_id")
    private Long entityId;

    public CharacterHasStatusEntity() {
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
