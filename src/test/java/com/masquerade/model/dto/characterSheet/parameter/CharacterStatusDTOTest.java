package com.masquerade.model.dto.characterSheet.parameter;

import com.masquerade.model.dto.characterSheet.CharacterListItemDTO;
import com.masquerade.model.entity.characterSheet.CharacterEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterStatusDTOTest {

    CharacterStatusDTO characterStatusDTO;

    @BeforeEach
    public void setUp() {
        characterStatusDTO = new CharacterStatusDTO();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = characterStatusDTO.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(characterStatusDTO));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */

    @Test
    void testConstructor() {
        characterStatusDTO = new CharacterStatusDTO(5L, "Jean");
        assertEquals(characterStatusDTO.getId(), 5L);
        assertEquals(characterStatusDTO.getName(), "Jean");

        characterStatusDTO = new CharacterStatusDTO(5L, "Jean", new ArrayList<>());
        assertNotNull(characterStatusDTO.getStatus());
        assertEquals(characterStatusDTO.getId(), 5L);
        assertEquals(characterStatusDTO.getName(), "Jean");
    }

    /* Setter / Getters */

    @Test
    void testId() {
        characterStatusDTO.setId(100L);
        assertEquals(100L, characterStatusDTO.getId());
    }

    @Test
    void testName() {
        characterStatusDTO.setName("Jojo");
        assertEquals("Jojo", characterStatusDTO.getName());
    }

    @Test
    void testStatus() {
        List<DeclaredStatusDTO> status = new ArrayList<>();
        DeclaredStatusDTO dto = new DeclaredStatusDTO(5L);
        dto.setDescriptionEN("Note");
        status.add(dto);
        characterStatusDTO.setStatus(status);
        assertEquals(5L, characterStatusDTO.getStatus().get(0).getEntity());
        assertEquals("Note", characterStatusDTO.getStatus().get(0).getDescriptionEN());
    }
}