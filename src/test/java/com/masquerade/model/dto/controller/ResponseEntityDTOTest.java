package com.masquerade.model.dto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ResponseEntityDTOTest {
    @Test
    public void testConstructor() {
        ResponseDTO responseDTO = new ResponseDTO(HttpStatus.BAD_REQUEST, "Bad request");
        ResponseEntityDTO<ResponseDTO> dto = new ResponseEntityDTO<>(responseDTO);
        assertEquals(dto.getStatusCode(), responseDTO.getHttpStatus());
    }
}