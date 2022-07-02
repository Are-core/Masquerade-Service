package com.masquerade.model.entity.characterSheet.skill;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.masquerade.model.entity.characterSheet.CharacterEntity;

import javax.persistence.*;

@Table(name="character_has_skill")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class CharacterHasSkillEntity {
    @EmbeddedId
    private CharacterHasSkillKey id;

    @ManyToOne
    @MapsId("character")
    @JoinColumn(name = "character_id")
    private CharacterEntity character;

    @ManyToOne
    @MapsId("skill")
    @JoinColumn(name = "skill_id")
    private SkillEntity skill;

    @Column(name = "level")
    private Integer level;

    @ManyToOne
    @JoinColumn(name="skill_specialization_id")
    private SkillSpecializationEntity skillSpecialization;

    public CharacterHasSkillEntity() {
    }

    public CharacterHasSkillEntity(CharacterHasSkillKey id, CharacterEntity character, SkillEntity skill, Integer level, SkillSpecializationEntity skillSpecialization) {
        this.id = id;
        this.character = character;
        this.skill = skill;
        this.level = level;
        this.skillSpecialization = skillSpecialization;
    }

    public boolean generateId() {
        if(emptyObjectCheck()) {
            return true;
        }
        this.id = new CharacterHasSkillKey(this.character.getId(), this.skill.getId());
        return false;
    }

    public boolean emptyObjectCheck() {
        return (this.id == null || this.character == null || this.skill == null);
    }

    public CharacterHasSkillKey getId() {
        return id;
    }

    public void setId(CharacterHasSkillKey id) {
        this.id = id;
    }

    public CharacterEntity getCharacter() {
        return character;
    }

    public void setCharacter(CharacterEntity character) {
        this.character = character;
    }

    public SkillEntity getSkill() {
        return skill;
    }

    public void setSkill(SkillEntity skill) {
        this.skill = skill;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public SkillSpecializationEntity getSkillSpecialization() {
        return skillSpecialization;
    }

    public void setSkillSpecialization(SkillSpecializationEntity skillSpecialization) {
        this.skillSpecialization = skillSpecialization;
    }
}

