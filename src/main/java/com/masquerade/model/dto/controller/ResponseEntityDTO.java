package com.masquerade.model.dto.controller;

import org.springframework.http.ResponseEntity;

public class ResponseEntityDTO<R> extends ResponseEntity<ResponseDTO> {
    public ResponseEntityDTO(ResponseDTO response) {
        super(response, response.getHttpStatus());
    }


}
