package com.masquerade.model.entity.characterSheet.parameter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class StatusTypeEntityTest {

    StatusTypeEntity statusTypeEntity;

    @BeforeEach
    public void setUp() {
        statusTypeEntity = new StatusTypeEntity();
    }

    @Test
    public void testNullFields()  {
        setUp();
        Field[] fields = statusTypeEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(statusTypeEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructor */

    @Test
    public void testConstructors() {
        statusTypeEntity = new StatusTypeEntity();
        assertNull(statusTypeEntity.getId());
        assertNull(statusTypeEntity.getDescriptionEN());
        assertNull(statusTypeEntity.getNoteEN());
        assertNull(statusTypeEntity.getDescriptionFR());
        assertNull(statusTypeEntity.getNoteFR());

        statusTypeEntity = new StatusTypeEntity("Text", "Text 2", "Note", "Note 2");
        assertNull(statusTypeEntity.getId());
        assertEquals("Text", statusTypeEntity.getDescriptionEN());
        assertEquals("Text 2", statusTypeEntity.getDescriptionFR());
        assertEquals("Note", statusTypeEntity.getNoteEN());
        assertEquals("Note 2", statusTypeEntity.getNoteFR());

        statusTypeEntity = new StatusTypeEntity(8L, "Text", "Text 2", "Note", "Note 2");
        assertEquals(8L, statusTypeEntity.getId());
        assertEquals("Text", statusTypeEntity.getDescriptionEN());
        assertEquals("Text 2", statusTypeEntity.getDescriptionFR());
        assertEquals("Note", statusTypeEntity.getNoteEN());
        assertEquals("Note 2", statusTypeEntity.getNoteFR());
    }

    /* Method */

    @Test
    public void testEmptyObject(){
        statusTypeEntity = new StatusTypeEntity();
        assertTrue(statusTypeEntity.emptyObjectCheck());

        statusTypeEntity = new StatusTypeEntity("Text", "Text 2", "Note", "Note 2");
        assertFalse(statusTypeEntity.emptyObjectCheck());
    }

    /* Setters / Getters */


    @Test
    void testId() {
        statusTypeEntity.setId(15L);
        assertEquals(15L, statusTypeEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        statusTypeEntity.setDescriptionEN("EN text");
        assertEquals("EN text", statusTypeEntity.getDescriptionEN());
    }

    @Test
    void testDescriptionFR() {
        statusTypeEntity.setDescriptionFR("FR text");
        assertEquals("FR text", statusTypeEntity.getDescriptionFR());
    }

    @Test
    void testNoteN() {
        statusTypeEntity.setNoteEN("EN text");
        assertEquals("EN text", statusTypeEntity.getNoteEN());
    }

    @Test
    void testNoteFR() {
        statusTypeEntity.setNoteFR("FR text");
        assertEquals("FR text", statusTypeEntity.getNoteFR());
    }

}