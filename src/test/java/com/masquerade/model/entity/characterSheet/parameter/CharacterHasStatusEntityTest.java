package com.masquerade.model.entity.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.CharacterEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CharacterHasStatusEntityTest {

    CharacterHasStatusEntity characterHasStatusEntity;

    @BeforeEach
    public void setUp() {
        characterHasStatusEntity = new CharacterHasStatusEntity();
    }

    @Test
    public void testNullFields()  {
        setUp();
        Field[] fields = characterHasStatusEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(characterHasStatusEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        characterHasStatusEntity = new CharacterHasStatusEntity();
        assertNull(characterHasStatusEntity.getCharacter());
        assertNull(characterHasStatusEntity.getEntityId());
        assertNull(characterHasStatusEntity.getStatus());
        assertNull(characterHasStatusEntity.getId());

        characterHasStatusEntity = new CharacterHasStatusEntity(new CharacterHasStatusKey(1L, 8L), 9L);
        assertEquals(8L, characterHasStatusEntity.getId().statusId);
        assertEquals(1L, characterHasStatusEntity.getId().characterId);
        assertNotNull(characterHasStatusEntity.getEntityId());
        assertEquals(9L, characterHasStatusEntity.getEntityId());

        characterHasStatusEntity = new CharacterHasStatusEntity(1L, 8L, 9L);
        assertEquals(8L, characterHasStatusEntity.getId().statusId);
        assertEquals(1L, characterHasStatusEntity.getId().characterId);
        assertNotNull(characterHasStatusEntity.getEntityId());
        assertEquals(9L, characterHasStatusEntity.getEntityId());
    }

    /* Setters / Getters */

    @Test
    public void testId(){
        characterHasStatusEntity.setId(new CharacterHasStatusKey(5L, 6L));
        assertNotNull(characterHasStatusEntity.getId());
        assertEquals(5L, characterHasStatusEntity.getId().characterId);
        assertEquals(6L, characterHasStatusEntity.getId().statusId);
    }

    @Test
    public void testEntityId(){
        characterHasStatusEntity.setEntityId(2L);
        assertNotNull(characterHasStatusEntity.getEntityId());
        assertEquals(2L, characterHasStatusEntity.getEntityId());
    }

    @Test
    public void testCharacter(){
        characterHasStatusEntity.setCharacter(new CharacterEntity());
        characterHasStatusEntity.getCharacter().setId(2L);
        assertNotNull(characterHasStatusEntity.getCharacter());
        assertEquals(2L, characterHasStatusEntity.getCharacter().getId());
    }

    @Test
    public void testStatus(){
        characterHasStatusEntity.setStatus(new StatusEntity());
        characterHasStatusEntity.getStatus().setId(2L);
        assertNotNull(characterHasStatusEntity.getStatus());
        assertEquals(2L, characterHasStatusEntity.getStatus().getId());
    }
}