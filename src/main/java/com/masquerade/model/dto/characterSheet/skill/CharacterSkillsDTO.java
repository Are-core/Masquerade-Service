package com.masquerade.model.dto.characterSheet.skill;

import java.util.List;

public class CharacterSkillsDTO {
    private Long id;
    private String name;
    private List<DeclaredSkillDTO> skills;

    public CharacterSkillsDTO() {
    }

    public CharacterSkillsDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CharacterSkillsDTO(Long id, String name, List<DeclaredSkillDTO> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeclaredSkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<DeclaredSkillDTO> skills) {
        this.skills = skills;
    }
}
