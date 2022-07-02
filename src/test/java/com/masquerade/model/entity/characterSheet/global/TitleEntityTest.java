package com.masquerade.model.entity.characterSheet.global;

import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TitleEntityTest {
    TitleEntity titleEntity;

    @BeforeEach
    public void setUp() {
        titleEntity = new TitleEntity();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = titleEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(titleEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        titleEntity = new TitleEntity();
        assertNull(titleEntity.getId());
        assertNull(titleEntity.getNoteEN());
        assertNull(titleEntity.getNoteFR());
        assertNull(titleEntity.getDescriptionEN());
        assertNull(titleEntity.getDescriptionFR());
        assertNull(titleEntity.getSect());

        titleEntity = new TitleEntity(new SectEntity(), "text", "text 2", "text 3", "text 4");
        assertNull(titleEntity.getId());
        assertNotNull(titleEntity.getNoteEN());
        assertEquals("text 3", titleEntity.getNoteEN());
        assertNotNull(titleEntity.getNoteFR());
        assertEquals("text 4", titleEntity.getNoteFR());
        assertNotNull(titleEntity.getDescriptionEN());
        assertEquals("text", titleEntity.getDescriptionEN());
        assertNotNull(titleEntity.getDescriptionFR());
        assertEquals("text 2", titleEntity.getDescriptionFR());
        assertNotNull(titleEntity.getSect());

        titleEntity = new TitleEntity(9L, new SectEntity(), "text", "text 2", "text 3", "text 4");
        assertNotNull(titleEntity.getId());
        assertEquals(9L, titleEntity.getId());
        assertNotNull(titleEntity.getNoteEN());
        assertEquals("text 3", titleEntity.getNoteEN());
        assertNotNull(titleEntity.getNoteFR());
        assertEquals("text 4", titleEntity.getNoteFR());
        assertNotNull(titleEntity.getDescriptionEN());
        assertEquals("text", titleEntity.getDescriptionEN());
        assertNotNull(titleEntity.getDescriptionFR());
        assertEquals("text 2", titleEntity.getDescriptionFR());
        assertNotNull(titleEntity.getSect());
    }

    /* Methods */
    @Test
    public void testEmptyCheck() {
        assertTrue(titleEntity.emptyObjectCheck());
        titleEntity = new TitleEntity(new SectEntity(), "text", "text 2", "text 3", "text 4");
        assertFalse(titleEntity.emptyObjectCheck());
    }

    @Test
    public void testIsUpdatable() {
        titleEntity.setId(null);
        assertFalse(titleEntity.isUpdatable());
        titleEntity.setId(2L);
        assertTrue(titleEntity.isUpdatable());
    }

    /* Setters / Getters */
    @Test
    void testId() {
        titleEntity.setId(15L);
        assertEquals(15L, titleEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        titleEntity.setDescriptionEN("EN text");
        assertEquals("EN text", titleEntity.getDescriptionEN());
    }

    @Test
    void testDescriptionFR() {
        titleEntity.setDescriptionFR("FR text");
        assertEquals("FR text", titleEntity.getDescriptionFR());
    }

    @Test
    void testNoteEN() {
        titleEntity.setNoteEN("text");
        assertEquals("text", titleEntity.getNoteEN());
    }

    @Test
    void testNoteFR() {
        titleEntity.setNoteFR("text");
        assertEquals("text", titleEntity.getNoteFR());
    }

    @Test
    void testSect() {
        SectEntity sect = new SectEntity();
        sect.setId(45L);
        titleEntity.setSect(sect);
        assertNotNull(titleEntity.getSect());
        assertEquals(45L, titleEntity.getSect().getId());
    }

    @Test
    void testStatus() {
        List<StatusEntity> statusList = new ArrayList<>();
        StatusEntity status = new StatusEntity();
        status.setId(2L);
        status.setDescriptionEN("test");
        statusList.add(status);
        titleEntity.setStatus(statusList);
        assertEquals("test", titleEntity.getStatus().get(0).getDescriptionEN());
        assertEquals(2L, titleEntity.getStatus().get(0).getId());
    }
}