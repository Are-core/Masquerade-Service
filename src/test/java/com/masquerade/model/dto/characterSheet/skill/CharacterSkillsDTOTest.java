package com.masquerade.model.dto.characterSheet.skill;

import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterSkillsDTOTest {
    CharacterSkillsDTO characterSkillsDTO;

    @BeforeEach
    public void setUp() {
        characterSkillsDTO = new CharacterSkillsDTO();
    }

    /* Constructors */
    @Test
    public void testConstructors(){
        characterSkillsDTO = new CharacterSkillsDTO();
        assertNull(characterSkillsDTO.getId());
        assertNull(characterSkillsDTO.getName());
        assertNull(characterSkillsDTO.getSkills());

        characterSkillsDTO = new CharacterSkillsDTO(8L, "name");
        assertEquals(8L, characterSkillsDTO.getId());
        assertEquals("name", characterSkillsDTO.getName());
        assertNull(characterSkillsDTO.getSkills());

        characterSkillsDTO = new CharacterSkillsDTO(8L, "name", new ArrayList<>());
        assertEquals(8L, characterSkillsDTO.getId());
        assertEquals("name", characterSkillsDTO.getName());
        assertNotNull(characterSkillsDTO.getSkills());
        assertTrue(characterSkillsDTO.getSkills().isEmpty());
    }

    /* Setters / Getters */
    @Test
    public void testId() {
        characterSkillsDTO.setId(47L);
        assertEquals(47L, characterSkillsDTO.getId());
    }

    @Test
    public void testName() {
        characterSkillsDTO.setName("John");
        assertEquals("John", characterSkillsDTO.getName());
    }

    @Test
    public void testSkills() {
        DeclaredSkillDTO skill = new DeclaredSkillDTO(7L, 0, "", "", false, false, "", "", 0, new SkillSpecializationEntity());
        List<DeclaredSkillDTO> skills = new ArrayList<>();
        skills.add(skill);
        characterSkillsDTO.setSkills(skills);
        assertEquals(7L, characterSkillsDTO.getSkills().get(0).getId());
    }
}