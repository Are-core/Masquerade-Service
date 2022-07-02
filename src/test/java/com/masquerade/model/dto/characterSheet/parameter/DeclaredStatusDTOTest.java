package com.masquerade.model.dto.characterSheet.parameter;

import com.masquerade.model.entity.characterSheet.global.SectEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusEntity;
import com.masquerade.model.entity.characterSheet.parameter.StatusTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DeclaredStatusDTOTest {
    DeclaredStatusDTO declaredStatusDTO;

    @BeforeEach
    public void setUp() {
        declaredStatusDTO = new DeclaredStatusDTO(1L);
    }

    /* Constructors */

    @Test
    public void testConstructors(){
        declaredStatusDTO = new DeclaredStatusDTO(15L);
        assertEquals(15L, declaredStatusDTO.getEntity());
        assertNull(declaredStatusDTO.getId());

        declaredStatusDTO = new DeclaredStatusDTO(2L, new SectEntity(), new StatusTypeEntity(), "", "", "", "", 8L);
        assertEquals(8L, declaredStatusDTO.getEntity());
        assertEquals(2L, declaredStatusDTO.getId());

        StatusEntity status = new StatusEntity();
        status.setId(55L);

        declaredStatusDTO = new DeclaredStatusDTO(status, 44L);
        assertEquals(44L, declaredStatusDTO.getEntity());
        assertEquals(55L, declaredStatusDTO.getId());
    }

    /* Setters / Getters */
    @Test
    public void testEntity() {
        declaredStatusDTO.setEntity(123L);
        assertEquals(123L, declaredStatusDTO.getEntity());
    }
}