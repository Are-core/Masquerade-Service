package com.masquerade.model.entity.characterSheet.skill;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.masquerade.model.entity.characterSheet.MasqueradeEntity;

import javax.persistence.*;

@Table(name="skill_specialization")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class SkillSpecializationEntity extends MasqueradeEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description_EN")
    private String descriptionEN;
    @Column(name = "description_FR")
    private String descriptionFR;

    public SkillSpecializationEntity() {
    }

    public SkillSpecializationEntity(String descriptionEN, String descriptionFR) {
        this.descriptionEN = descriptionEN;
        this.descriptionFR = descriptionFR;
    }

    public SkillSpecializationEntity(Long id, String descriptionEN, String descriptionFR) {
        this(descriptionEN, descriptionFR);
        this.id = id;
    }

    public boolean isUpdatable() {
        return this.id != null && this.id > 0L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionEN() {
        return descriptionEN;
    }

    public void setDescriptionEN(String descriptionEN) {
        this.descriptionEN = descriptionEN;
    }

    public String getDescriptionFR() {
        return descriptionFR;
    }

    public void setDescriptionFR(String descriptionFR) {
        this.descriptionFR = descriptionFR;
    }
}
