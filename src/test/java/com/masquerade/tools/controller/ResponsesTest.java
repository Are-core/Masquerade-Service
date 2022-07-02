package com.masquerade.tools.controller;

import com.masquerade.model.dto.controller.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponsesTest {

    @Test
    void missingArgumentsWithEmptyList() {
        ResponseDTO response = Responses.MissingArguments(new ArrayList<>());
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getMessage());
    }

    @Test
    void missingArgumentsWithList() {
        List<String> arguments = new ArrayList<>();
        arguments.add("id");
        arguments.add("name");
        ResponseDTO response = Responses.MissingArguments(arguments);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertEquals("id - name : missing arguments",response.getMessage());
    }

    @Test
    void missingArgumentsWithNullArgumentList() {
        ResponseDTO response = Responses.MissingArguments(null);
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertNull(response.getMessage());
    }

    @Test
    void missingArgument() {
        ResponseDTO response = Responses.MissingArgument("test");
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getHttpStatus());
        assertEquals("test : missing argument",response.getMessage());
    }

    @Test
    void formatResponse() {
        ResponseDTO responseDto = new ResponseDTO(HttpStatus.NO_CONTENT);
        ResponseDTO response = Responses.FormatResponse(responseDto, "test");
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertEquals("test",response.getMessage());
    }

    @Test
    void formatResponseWithNullMessage() {
        ResponseDTO responseDto = new ResponseDTO(HttpStatus.NO_CONTENT);
        ResponseDTO response = Responses.FormatResponse(responseDto, null);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getHttpStatus());
        assertNull(response.getMessage());
    }
}