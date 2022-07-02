package com.masquerade.model.entity.characterSheet.global;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class SectEntityTest {
    SectEntity sectEntity;

    @BeforeEach
    public void setUp() {
        sectEntity = new SectEntity();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = sectEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(sectEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        sectEntity = new SectEntity();
        assertNull(sectEntity.getId());
        assertNull(sectEntity.getDescription());

        sectEntity = new SectEntity("text");
        assertNull(sectEntity.getId());
        assertNotNull(sectEntity.getDescription());
        assertEquals("text", sectEntity.getDescription());

        sectEntity = new SectEntity(9L,"text");
        assertNotNull(sectEntity.getId());
        assertEquals(9L, sectEntity.getId());
        assertNotNull(sectEntity.getDescription());
        assertEquals("text", sectEntity.getDescription());
    }

    /* Methods */
    @Test
    public void testEmptyCheck() {
        assertTrue(sectEntity.emptyObjectCheck());
        sectEntity = new SectEntity("text");
        assertFalse(sectEntity.emptyObjectCheck());
    }

    @Test
    public void testIsUpdatable() {
        sectEntity.setId(null);
        assertFalse(sectEntity.isUpdatable());
        sectEntity.setId(2L);
        assertTrue(sectEntity.isUpdatable());
    }

    /* Setters / Getters */
    @Test
    void testId() {
        sectEntity.setId(15L);
        assertEquals(15L, sectEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        sectEntity.setDescription("EN text");
        assertEquals("EN text", sectEntity.getDescription());
    }
}