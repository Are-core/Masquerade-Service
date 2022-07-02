package com.masquerade.model.dto.controller;

import com.masquerade.model.entity.characterSheet.global.ArchetypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ResponseDTOTest {
    ResponseDTO responseDTO;

    @BeforeEach
    public void setUp() {
        responseDTO = new ResponseDTO(HttpStatus.OK);
    }

    /* Constructors */
    @Test
    public void testConstructors() {
        assertEquals(responseDTO.getHttpStatus(), HttpStatus.OK);
        assertNull(responseDTO.getMessage());
        assertNull(responseDTO.getBody());

        responseDTO = new ResponseDTO(HttpStatus.NO_CONTENT, "No content") ;
        assertEquals(responseDTO.getHttpStatus(), HttpStatus.NO_CONTENT);
        assertEquals(responseDTO.getMessage(), "No content");
        assertNull(responseDTO.getBody());

        responseDTO = new ResponseDTO(HttpStatus.NO_CONTENT, 5) ;
        assertEquals(responseDTO.getHttpStatus(), HttpStatus.NO_CONTENT);
        assertEquals(responseDTO.getBody(), 5);
        assertNull(responseDTO.getMessage());

        responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, "Bad request", 5F) ;
        assertEquals(responseDTO.getHttpStatus(), HttpStatus.BAD_REQUEST);
        assertEquals(responseDTO.getBody(), 5F);
        assertEquals(responseDTO.getMessage(), "Bad request");
    }

    /* Setters / Getters */
    @Test
    public void testHttpStatus() {
        responseDTO.setCode(HttpStatus.CREATED);
        assertEquals(HttpStatus.CREATED, responseDTO.getHttpStatus());
        assertEquals(HttpStatus.CREATED.value(), responseDTO.getCode());
    }

    @Test
    public void testMessage() {
        responseDTO.setMessage("Ok");
        assertEquals("Ok", responseDTO.getMessage());
    }

    @Test
    public void testObject() {
        responseDTO.setBody(2.0);
        assertEquals(2.0, responseDTO.getBody());
    }
}