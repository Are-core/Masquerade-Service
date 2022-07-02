package com.masquerade.model.entity.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.global.SectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class StatusEntityTest {

    StatusEntity statusEntity;

    @BeforeEach
    public void setUp() {
        statusEntity = new StatusEntity();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = statusEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(statusEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        statusEntity = new StatusEntity();
        assertNull(statusEntity.getId());
        assertNull(statusEntity.getStatusType());
        assertNull(statusEntity.getNoteEN());
        assertNull(statusEntity.getNoteFR());
        assertNull(statusEntity.getDescriptionEN());
        assertNull(statusEntity.getDescriptionFR());
        assertNull(statusEntity.getStatusType());
        assertNull(statusEntity.getSect());

        statusEntity = new StatusEntity(new SectEntity(), new StatusTypeEntity(), "text", "text 2", "text 3", "text 4");
        assertNull(statusEntity.getId());
        assertNotNull(statusEntity.getStatusType());
        assertNotNull(statusEntity.getNoteEN());
        assertEquals("text 3", statusEntity.getNoteEN());
        assertNotNull(statusEntity.getNoteFR());
        assertEquals("text 4", statusEntity.getNoteFR());
        assertNotNull(statusEntity.getDescriptionEN());
        assertEquals("text", statusEntity.getDescriptionEN());
        assertNotNull(statusEntity.getDescriptionFR());
        assertEquals("text 2", statusEntity.getDescriptionFR());
        assertNotNull(statusEntity.getStatusType());
        assertNotNull(statusEntity.getSect());

        statusEntity = new StatusEntity(9L, new SectEntity(), new StatusTypeEntity(), "text", "text 2", "text 3", "text 4");
        assertNotNull(statusEntity.getId());
        assertEquals(9L, statusEntity.getId());
        assertNotNull(statusEntity.getStatusType());
        assertNotNull(statusEntity.getNoteEN());
        assertEquals("text 3", statusEntity.getNoteEN());
        assertNotNull(statusEntity.getNoteFR());
        assertEquals("text 4", statusEntity.getNoteFR());
        assertNotNull(statusEntity.getDescriptionEN());
        assertEquals("text", statusEntity.getDescriptionEN());
        assertNotNull(statusEntity.getDescriptionFR());
        assertEquals("text 2", statusEntity.getDescriptionFR());
        assertNotNull(statusEntity.getStatusType());
        assertNotNull(statusEntity.getSect());
    }

    /* Methods */
    @Test
    public void testEmptyCheck() {
        assertTrue(statusEntity.emptyObjectCheck());
        statusEntity = new StatusEntity(new SectEntity(), new StatusTypeEntity(), "text", "text 2", "text 3", "text 4");
        assertFalse(statusEntity.emptyObjectCheck());
    }

    @Test
    public void testIsUpdatable() {
        statusEntity.setId(null);
        assertFalse(statusEntity.isUpdatable());
        statusEntity.setId(2L);
        assertTrue(statusEntity.isUpdatable());
    }

    /* Setters / Getters */
    @Test
    void testId() {
        statusEntity.setId(15L);
        assertEquals(15L, statusEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        statusEntity.setDescriptionEN("EN text");
        assertEquals("EN text", statusEntity.getDescriptionEN());
    }

    @Test
    void testDescriptionFR() {
        statusEntity.setDescriptionFR("FR text");
        assertEquals("FR text", statusEntity.getDescriptionFR());
    }

    @Test
    void testNoteEN() {
        statusEntity.setNoteEN("text");
        assertEquals("text", statusEntity.getNoteEN());
    }

    @Test
    void testNoteFR() {
        statusEntity.setNoteFR("text");
        assertEquals("text", statusEntity.getNoteFR());
    }

    @Test
    void testSect() {
        SectEntity sect = new SectEntity();
        sect.setId(45L);
        statusEntity.setSect(sect);
        assertNotNull(statusEntity.getSect());
        assertEquals(45L, statusEntity.getSect().getId());
    }

    @Test
    void testStatusType() {
        StatusTypeEntity statusType = new StatusTypeEntity();
        statusType.setId(45L);
        statusEntity.setStatusType(statusType);
        assertNotNull(statusEntity.getStatusType());
        assertEquals(45L, statusEntity.getStatusType().getId());
    }
}