package com.masquerade.model.entity.characterSheet.skill;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

@Table(name="skill")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class SkillEntity {
    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cost_id")
    private Integer costId;

    @Column(name = "description_EN")
    private String descriptionEN;
    @Column(name = "description_FR")
    private String descriptionFR;

    @ManyToMany
    @JoinTable(
            name = "skill_has_skill_specialization",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_specialization_id"))
    private List<SkillSpecializationEntity> specializations;

    @Column(name = "is_duplicable")
    private Boolean isDuplicable;
    @Column(name = "has_specialization")
    private Boolean hasSpecialization;

    @Column(name = "note_EN")
    private String noteEN;
    @Column(name = "note_FR")
    private String noteFR;

    public SkillEntity() {
    }

    public SkillEntity(Long id, Integer costId, String descriptionEN, String descriptionFR, Boolean isDuplicable, Boolean hasSpecialization, String noteEN, String noteFR) {
        this.id = id;
        this.costId = costId;
        this.descriptionEN = descriptionEN;
        this.descriptionFR = descriptionFR;
        this.isDuplicable = isDuplicable;
        this.hasSpecialization = hasSpecialization;
        this.noteEN = noteEN;
        this.noteFR = noteFR;
    }

    public boolean emptyObjectCheck() {
        return (this.getDescriptionEN() == null &&
                this.getDescriptionFR() == null &&
                this.getNoteEN() == null &&
                this.getNoteFR() == null &&
                this.getCostId() == null );
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

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
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

    public Boolean getDuplicable() {
        return isDuplicable;
    }

    public void setDuplicable(Boolean duplicable) {
        isDuplicable = duplicable;
    }

    public Boolean getHasSpecialization() {
        return hasSpecialization;
    }

    public void setHasSpecialization(Boolean hasSpecialization) {
        this.hasSpecialization = hasSpecialization;
    }

    public String getNoteEN() {
        return noteEN;
    }

    public void setNoteEN(String noteEN) {
        this.noteEN = noteEN;
    }

    public String getNoteFR() {
        return noteFR;
    }

    public void setNoteFR(String noteFR) {
        this.noteFR = noteFR;
    }

    public List<SkillSpecializationEntity> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<SkillSpecializationEntity> specializations) {
        this.specializations = specializations;
    }
}
