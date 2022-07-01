package com.masquerade.model.dto.characterSheet.skill;

import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;

public class DeclaredSkillDTO extends SkillEntity {
    private Integer level;
    private SkillSpecializationEntity specialization;

    public DeclaredSkillDTO(Long id, Integer costId, String descriptionEN, String descriptionFR, Boolean isDuplicable, Boolean hasSpecialization, String noteEN, String noteFR, Integer level, SkillSpecializationEntity specialization) {
        super(id, costId, descriptionEN, descriptionFR, isDuplicable, hasSpecialization, noteEN, noteFR);
        this.level = level;
        this.specialization = specialization;
    }

    public DeclaredSkillDTO(SkillEntity skillEntity, Integer level, SkillSpecializationEntity specialization) {
        super(skillEntity.getId(), skillEntity.getCostId(), skillEntity.getDescriptionEN(), skillEntity.getDescriptionFR(), skillEntity.getDuplicable(), skillEntity.getHasSpecialization(), skillEntity.getNoteEN(), skillEntity.getNoteFR());
        this.level = level;
        this.specialization = specialization;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public SkillSpecializationEntity getSpecialization() {
        return specialization;
    }

    public void setSpecialization(SkillSpecializationEntity specialization) {
        this.specialization = specialization;
    }
}
