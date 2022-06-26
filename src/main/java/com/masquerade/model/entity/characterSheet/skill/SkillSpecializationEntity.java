package com.masquerade.model.entity.characterSheet.skill;

import javax.persistence.*;

@Table(name="skill_specialization")
@Entity
public class SkillSpecializationEntity {
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

    public boolean emptyObjectCheck() {
        return (this.getDescriptionEN() == null &&
                this.getDescriptionFR() == null);
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
