package com.masquerade.model.entity.characterSheet.global;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ClanEntityTest {
    ClanEntity clanEntity;

    @BeforeEach
    public void setUp() {
        clanEntity = new ClanEntity();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = clanEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(clanEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        clanEntity = new ClanEntity();
        assertNull(clanEntity.getId());
        assertNull(clanEntity.getDescriptionEN());
        assertNull(clanEntity.getDescriptionFR());
        assertNull(clanEntity.getNoteEN());
        assertNull(clanEntity.getNoteFR());

        clanEntity = new ClanEntity("text","","","");
        assertNull(clanEntity.getId());
        assertNotNull(clanEntity.getDescriptionEN());
        assertEquals("text", clanEntity.getDescriptionEN());

        clanEntity = new ClanEntity(9L,"text","","","");
        assertNotNull(clanEntity.getId());
        assertEquals(9L, clanEntity.getId());
        assertNotNull(clanEntity.getDescriptionEN());
        assertEquals("text", clanEntity.getDescriptionEN());
    }

    /* Methods */
    @Test
    public void testEmptyCheck() {
        assertTrue(clanEntity.emptyObjectCheck());
        clanEntity = new ClanEntity("text","","","");
        assertFalse(clanEntity.emptyObjectCheck());
    }

    @Test
    public void testIsUpdatable() {
        clanEntity.setId(null);
        assertFalse(clanEntity.isUpdatable());
        clanEntity.setId(2L);
        assertTrue(clanEntity.isUpdatable());
    }

    /* Setters / Getters */
    @Test
    void testId() {
        clanEntity.setId(15L);
        assertEquals(15L, clanEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        clanEntity.setDescriptionEN("EN text");
        assertEquals("EN text", clanEntity.getDescriptionEN());
    }

    @Test
    void testDescriptionFR() {
        clanEntity.setDescriptionFR("FR text");
        assertEquals("FR text", clanEntity.getDescriptionFR());
    }

    @Test
    void testNoteEN() {
        clanEntity.setNoteEN("text");
        assertEquals("text", clanEntity.getNoteEN());
    }

    @Test
    void testNoteFR() {
        clanEntity.setNoteFR("text");
        assertEquals("text", clanEntity.getNoteFR());
    }
}