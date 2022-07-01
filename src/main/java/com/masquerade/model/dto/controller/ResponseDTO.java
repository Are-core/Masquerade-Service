package com.masquerade.model.dto.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.beans.Transient;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO {
    private HttpStatus code;
    private String message;

    private Object body;

    public ResponseDTO(HttpStatus code, String message, Object body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public ResponseDTO(HttpStatus code, Object body) {
        this.code = code;
        this.message = null;
        this.body = body;
    }

    public ResponseDTO(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
        this.body = null;
    }

    public ResponseDTO(HttpStatus code) {
        this.code = code;
        this.message = null;
        this.body = null;
    }

    public Integer getCode() {
        return code.value();
    }

    @Transient
    public HttpStatus getHttpStatus() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
