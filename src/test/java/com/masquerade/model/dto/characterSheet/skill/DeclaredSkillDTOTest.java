package com.masquerade.model.dto.characterSheet.skill;

import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeclaredSkillDTOTest {
    DeclaredSkillDTO declaredSkillDTO;

    @BeforeEach
    public void setUp() {
        declaredSkillDTO = new DeclaredSkillDTO();
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        assertNull(declaredSkillDTO.getLevel());
        assertNull(declaredSkillDTO.getSpecialization());

        declaredSkillDTO = new DeclaredSkillDTO(5L, 0, "", "", false, false, "" , "", 0, new SkillSpecializationEntity());
        assertEquals(5L, declaredSkillDTO.getId());
        assertEquals("", declaredSkillDTO.getDescriptionEN());
        declaredSkillDTO = new DeclaredSkillDTO(new DeclaredSkillDTO(), 0, new SkillSpecializationEntity());
        assertEquals(0, declaredSkillDTO.getLevel());
        assertNotNull(declaredSkillDTO.getSpecialization());
    }

    /* Setter / Getters */
    @Test
    public void testLevel() {
        declaredSkillDTO.setLevel(9);
        assertEquals(9, declaredSkillDTO.getLevel());
    }

    @Test
    public void testSpecialization() {
        declaredSkillDTO.setSpecialization(new SkillSpecializationEntity());
        declaredSkillDTO.getSpecialization().setId(55L);
        assertEquals(55L, declaredSkillDTO.getSpecialization().getId());
    }
}