package com.masquerade.model.entity.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.global.SectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JurisdictionEntityTest {
    JurisdictionEntity jurisdictionEntity;

    @BeforeEach
    public void setUp() {
        jurisdictionEntity = new JurisdictionEntity();
    }

    @Test
    public void testNullFields() {
        setUp();
        Field[] fields = jurisdictionEntity.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (!field.getName().equals("__$lineHits$__")) {
                    assertNull(field.get(jurisdictionEntity));
                }
            } catch (Exception e) {
                fail();
            }
        }
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        jurisdictionEntity = new JurisdictionEntity();
        assertNull(jurisdictionEntity.getId());
        assertNull(jurisdictionEntity.getDescription());

        jurisdictionEntity = new JurisdictionEntity("text");
        assertNull(jurisdictionEntity.getId());
        assertNotNull(jurisdictionEntity.getDescription());
        assertEquals("text", jurisdictionEntity.getDescription());

        jurisdictionEntity = new JurisdictionEntity(9L,"text");
        assertNotNull(jurisdictionEntity.getId());
        assertEquals(9L, jurisdictionEntity.getId());
        assertNotNull(jurisdictionEntity.getDescription());
        assertEquals("text", jurisdictionEntity.getDescription());
    }

    /* Methods */
    @Test
    public void testEmptyCheck() {
        assertTrue(jurisdictionEntity.emptyObjectCheck());
        jurisdictionEntity = new JurisdictionEntity("text");
        assertFalse(jurisdictionEntity.emptyObjectCheck());
        jurisdictionEntity = new JurisdictionEntity(1L,"text");
        assertFalse(jurisdictionEntity.emptyObjectCheck());
    }

    @Test
    public void testIsUpdatable() {
        jurisdictionEntity.setId(null);
        assertFalse(jurisdictionEntity.isUpdatable());
        jurisdictionEntity.setId(2L);
        assertTrue(jurisdictionEntity.isUpdatable());
    }

    /* Setters / Getters */
    @Test
    void testId() {
        jurisdictionEntity.setId(15L);
        assertEquals(15L, jurisdictionEntity.getId());
    }

    @Test
    void testDescriptionEN() {
        jurisdictionEntity.setDescription("EN text");
        assertEquals("EN text", jurisdictionEntity.getDescription());
    }
}