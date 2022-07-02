package com.masquerade.model.entity.characterSheet.skill;

import com.masquerade.model.entity.characterSheet.CharacterEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CharacterHasSkillEntityTest {
    CharacterHasSkillEntity characterHasSkillEntity;

    @BeforeEach
    public void setUp() {
        characterHasSkillEntity = new CharacterHasSkillEntity();
    }

    @Test
    public void testNullFields()  {
        setUp();
        Field[] fields = characterHasSkillEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(characterHasSkillEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructor */
    @Test
    public void testConstructor() {
        characterHasSkillEntity = new CharacterHasSkillEntity();
        assertNull(characterHasSkillEntity.getId());
        assertNull(characterHasSkillEntity.getCharacter());
        assertNull(characterHasSkillEntity.getSkill());
        assertNull(characterHasSkillEntity.getSkillSpecialization());
        assertNull(characterHasSkillEntity.getLevel());

        characterHasSkillEntity = new CharacterHasSkillEntity(new CharacterHasSkillKey(1L, 2L), new CharacterEntity(), new SkillEntity(), 0, new SkillSpecializationEntity());
        assertEquals(2L, characterHasSkillEntity.getId().skillId);
        assertEquals(1L, characterHasSkillEntity.getId().characterId);
        assertNotNull(characterHasSkillEntity.getCharacter());
        assertNotNull(characterHasSkillEntity.getSkill());
        assertNotNull(characterHasSkillEntity.getSkillSpecialization());
        assertNotNull(characterHasSkillEntity.getLevel());
    }

    /* Methods */

    @Test
    public void testGenerateId() {
        characterHasSkillEntity = new CharacterHasSkillEntity();
        characterHasSkillEntity.setId(new CharacterHasSkillKey(5L, 9L));
        characterHasSkillEntity.setCharacter(new CharacterEntity());
        characterHasSkillEntity.setSkill(new SkillEntity());
        assertFalse(characterHasSkillEntity.generateId());
    }

    @Test
    public void testGenerateIdWithEmptyObject() {
        assertTrue(characterHasSkillEntity.generateId());
    }

    /* Setters / Getters */

    @Test
    public void testId() {
        characterHasSkillEntity.setId(new CharacterHasSkillKey(8L, 2L));
        assertEquals(8L, characterHasSkillEntity.getId().characterId);
        assertEquals(2L, characterHasSkillEntity.getId().skillId);
    }

    @Test
    public void testCharacter() {
        characterHasSkillEntity.setCharacter(new CharacterEntity());
        assertNotNull(characterHasSkillEntity.getCharacter());
    }

    @Test
    public void testSkill() {
        characterHasSkillEntity.setSkill(new SkillEntity());
        assertNotNull(characterHasSkillEntity.getSkill());
    }

    @Test
    public void testLevel() {
        characterHasSkillEntity.setLevel(25);
        assertEquals(25, characterHasSkillEntity.getLevel());
    }

    @Test
    public void testSpecialization() {
        characterHasSkillEntity.setSkillSpecialization(new SkillSpecializationEntity());
        assertNotNull(characterHasSkillEntity.getSkillSpecialization());
    }
}