package com.masquerade.model.dto.skill;

import com.masquerade.model.entity.characterSheet.skill.SkillEntity;
import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SkillEntityTest {

    SkillEntity skillEntity;

    @BeforeEach
    public void setUp() {
        skillEntity = new SkillEntity();
    }

    /* Constructor */

    @Test
    void testConstructor() {
        skillEntity = new SkillEntity();
        assertTrue(skillEntity.isEmpty());

        skillEntity = new SkillEntity(25L, null, null, null, null, null, null, null);
        assertEquals(25L, skillEntity.getId());
    }

    @Test
    void testUpdatable() {
        assertFalse(skillEntity.isUpdatable());
        skillEntity.setId(5L);
        assertTrue(skillEntity.isUpdatable());
    }

    /* Setters / Getters */

    @Test
    void testId() {
        skillEntity.setId(15L);
        assertEquals(15L, skillEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        skillEntity.setDescriptionEN("EN text");
        assertEquals("EN text", skillEntity.getDescriptionEN());
    }

    @Test
    void testDescriptionFR() {
        skillEntity.setDescriptionFR("FR text");
        assertEquals("FR text", skillEntity.getDescriptionFR());
    }

    @Test
    void testCost() {
        skillEntity.setCostId(5);
        assertEquals(5, skillEntity.getCostId());
    }

    @Test
    void testIsDuplicable() {
        skillEntity.setDuplicable(false);
        assertEquals(false, skillEntity.getDuplicable());
        skillEntity.setDuplicable(true);
        assertEquals(true, skillEntity.getDuplicable());
    }

    @Test
    void testHasSpecialization() {
        skillEntity.setHasSpecialization(false);
        assertEquals(false, skillEntity.getHasSpecialization());
        skillEntity.setHasSpecialization(true);
        assertEquals(true, skillEntity.getHasSpecialization());
    }

    @Test
    void testNoteEN() {
        skillEntity.setNoteEN("text");
        assertEquals("text", skillEntity.getNoteEN());
    }

    @Test
    void testNoteFR() {
        skillEntity.setNoteFR("text");
        assertEquals("text", skillEntity.getNoteFR());
    }

    @Test
    void testSpecializations() {
        List<SkillSpecializationEntity> specializations = new ArrayList<>();
        skillEntity.setSpecializations(specializations);
        assertTrue(skillEntity.getSpecializations().isEmpty());
        specializations.add(new SkillSpecializationEntity());
        skillEntity.setSpecializations(specializations);
        assertEquals(1, skillEntity.getSpecializations().size());
    }
}