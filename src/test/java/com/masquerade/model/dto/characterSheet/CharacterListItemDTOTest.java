package com.masquerade.model.dto.characterSheet;

import com.masquerade.model.entity.characterSheet.CharacterEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CharacterListItemDTOTest {
    CharacterListItemDTO characterListItemDTO;

    @BeforeEach
    public void setUp() {
        characterListItemDTO = new CharacterListItemDTO();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = characterListItemDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(characterListItemDTO));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructor */

    @Test
    void testConstructor() {
        CharacterEntity entity = new CharacterEntity(8L, true, false, "Roberto", "Dracula", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        characterListItemDTO = new CharacterListItemDTO(entity);
        assertEquals(entity.getId(), characterListItemDTO.getId());
        assertEquals(entity.getArchived(), characterListItemDTO.getArchived());
        assertEquals(entity.getName(), characterListItemDTO.getName());
        assertEquals(entity.getNpc(), characterListItemDTO.getNpc());
        assertEquals(entity.getPlayer(), characterListItemDTO.getPlayer());
    }

    /* Setter / Getters */

    @Test
    void testId() {
        characterListItemDTO.setId(100L);
        assertEquals(100L, characterListItemDTO.getId());
    }

    @Test
    void testArchived() {
        characterListItemDTO.setArchived(true);
        assertEquals(true, characterListItemDTO.getArchived());
        characterListItemDTO.setArchived(false);
        assertEquals(false, characterListItemDTO.getArchived());
    }

    @Test
    void testNpc() {
        characterListItemDTO.setNpc(true);
        assertEquals(true, characterListItemDTO.getNpc());
        characterListItemDTO.setNpc(false);
        assertEquals(false, characterListItemDTO.getNpc());
    }

    @Test
    void testPlayer() {
        characterListItemDTO.setPlayer("Jo");
        assertEquals("Jo", characterListItemDTO.getPlayer());
    }

    @Test
    void testName() {
        characterListItemDTO.setName("Patrick");
        assertEquals("Patrick", characterListItemDTO.getName());
    }
}