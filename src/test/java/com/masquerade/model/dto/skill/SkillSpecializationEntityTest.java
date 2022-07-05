package com.masquerade.model.dto.skill;

import com.masquerade.model.entity.characterSheet.skill.SkillSpecializationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class SkillSpecializationEntityTest {

    SkillSpecializationEntity skillSpecialization;

    @BeforeEach
    public void setUp() {
        skillSpecialization = new SkillSpecializationEntity();
    }

    @Test
    public void testNullFields()  {
        setUp();
        Field[] fields = skillSpecialization.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(skillSpecialization));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructor */

    @Test
    void testConstructor() {
        skillSpecialization = new SkillSpecializationEntity();
        assertTrue(skillSpecialization.isEmpty());
    }

    @Test
    void testConstructorWithValues() {
        skillSpecialization = new SkillSpecializationEntity("EN text", "FR text");
        assertFalse(skillSpecialization.isEmpty());
        assertEquals("FR text", skillSpecialization.getDescriptionFR());
    }

    @Test
    void testConstructorWithAllValues() {
        skillSpecialization = new SkillSpecializationEntity(5L, "EN text", "FR text");
        assertFalse(skillSpecialization.isEmpty());
        assertEquals(5L, skillSpecialization.getId());
    }

    @Test
    void testUpdatable() {
        skillSpecialization = new SkillSpecializationEntity("EN text", "FR text");
        assertFalse(skillSpecialization.isUpdatable());
        skillSpecialization = new SkillSpecializationEntity(5L, "EN text", "FR text");
        assertTrue(skillSpecialization.isUpdatable());
    }

    /* Setters / Getters */

    @Test
    void testId() {
        skillSpecialization.setId(15L);
        assertEquals(15L, skillSpecialization.getId());
    }

    @Test
    void testDescriptionEN() {
        skillSpecialization.setDescriptionEN("EN text");
        assertEquals("EN text", skillSpecialization.getDescriptionEN());
    }

    @Test
    void testDescriptionFR() {
        skillSpecialization.setDescriptionFR("FR text");
        assertEquals("FR text", skillSpecialization.getDescriptionFR());
    }
}