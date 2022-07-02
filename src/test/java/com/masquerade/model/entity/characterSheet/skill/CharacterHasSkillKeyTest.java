package com.masquerade.model.entity.characterSheet.skill;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CharacterHasSkillKeyTest {
    CharacterHasSkillKey characterHasSkillKey;

    @BeforeEach
    public void setUp() {
        characterHasSkillKey = new CharacterHasSkillKey();
    }

    @Test
    public void testNullFields() {
        Field[] fields = characterHasSkillKey.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(characterHasSkillKey));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    public void testConstructor() {
        characterHasSkillKey = new CharacterHasSkillKey();
        assertNull(characterHasSkillKey.characterId);
        assertNull(characterHasSkillKey.skillId);
        characterHasSkillKey = new CharacterHasSkillKey(1L,2L);
        assertEquals(1L, characterHasSkillKey.characterId);
        assertEquals(2L, characterHasSkillKey.skillId);
    }
}