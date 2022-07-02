package com.masquerade.model.entity.characterSheet.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CharacterHasStatusKeyTest {

    CharacterHasStatusKey characterHasStatusKey;

    @BeforeEach
    public void setUp() {
        characterHasStatusKey = new CharacterHasStatusKey();
    }

    @Test
    public void testNullFields() {
        Field[] fields = characterHasStatusKey.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(characterHasStatusKey));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    public void testConstructor() {
        characterHasStatusKey = new CharacterHasStatusKey();
        assertNull(characterHasStatusKey.characterId);
        assertNull(characterHasStatusKey.statusId);
        characterHasStatusKey = new CharacterHasStatusKey(1L,2L);
        assertEquals(1L, characterHasStatusKey.characterId);
        assertEquals(2L, characterHasStatusKey.statusId);
    }

}