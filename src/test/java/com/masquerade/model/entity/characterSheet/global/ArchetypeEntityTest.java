package com.masquerade.model.entity.characterSheet.global;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class ArchetypeEntityTest {
    ArchetypeEntity archetypeEntity;

    @BeforeEach
    public void setUp() {
        archetypeEntity = new ArchetypeEntity();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = archetypeEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(archetypeEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        archetypeEntity = new ArchetypeEntity();
        assertNull(archetypeEntity.getId());
        assertNull(archetypeEntity.getDescriptionEN());
        assertNull(archetypeEntity.getDescriptionFR());
        assertNull(archetypeEntity.getNoteEN());
        assertNull(archetypeEntity.getNoteFR());

        archetypeEntity = new ArchetypeEntity("text","","","");
        assertNull(archetypeEntity.getId());
        assertNotNull(archetypeEntity.getDescriptionEN());
        assertEquals("text", archetypeEntity.getDescriptionEN());

        archetypeEntity = new ArchetypeEntity(9L,"text","","","");
        assertNotNull(archetypeEntity.getId());
        assertEquals(9L, archetypeEntity.getId());
        assertNotNull(archetypeEntity.getDescriptionEN());
        assertEquals("text", archetypeEntity.getDescriptionEN());
    }

    /* Methods */
    @Test
    public void testEmptyCheck() {
        assertTrue(archetypeEntity.emptyObjectCheck());
        archetypeEntity = new ArchetypeEntity("text","","","");
        assertFalse(archetypeEntity.emptyObjectCheck());
    }

    @Test
    public void testIsUpdatable() {
        archetypeEntity.setId(null);
        assertFalse(archetypeEntity.isUpdatable());
        archetypeEntity.setId(2L);
        assertTrue(archetypeEntity.isUpdatable());
    }

    /* Setters / Getters */
    @Test
    void testId() {
        archetypeEntity.setId(15L);
        assertEquals(15L, archetypeEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        archetypeEntity.setDescriptionEN("EN text");
        assertEquals("EN text", archetypeEntity.getDescriptionEN());
    }

    @Test
    void testDescriptionFR() {
        archetypeEntity.setDescriptionFR("FR text");
        assertEquals("FR text", archetypeEntity.getDescriptionFR());
    }

    @Test
    void testNoteEN() {
        archetypeEntity.setNoteEN("EN text");
        assertEquals("EN text", archetypeEntity.getNoteEN());
    }

    @Test
    void testNoteFR() {
        archetypeEntity.setNoteFR("FR text");
        assertEquals("FR text", archetypeEntity.getNoteFR());
    }
}