package com.masquerade.tools.controller;

import com.masquerade.model.dto.controller.ResponseDTO;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Responses {
    public static final ResponseDTO ResponseNotFound = new ResponseDTO(HttpStatus.NOT_FOUND);
    public static final ResponseDTO ResponseNoContent = new ResponseDTO(HttpStatus.NO_CONTENT);
    public static final ResponseDTO ResponseBadRequest = new ResponseDTO(HttpStatus.BAD_REQUEST);

    public static ResponseDTO MissingArgument(String argument) {
        List<String> arguments = new ArrayList<>();
        arguments.add(argument);
        return MissingArguments(arguments);
    }

    public static ResponseDTO MissingArguments(List<String> arguments) {
        ResponseDTO response = new ResponseDTO(HttpStatus.BAD_REQUEST);
        if(arguments != null && !arguments.isEmpty()) {
            StringJoiner str = new StringJoiner(" - ", "", "");
            for (String argument : arguments) {
                str.add(argument);
            }
            response.setMessage(String.format("%s : missing argument%s", str, arguments.size() > 1 ? "s" : ""));
        }
        return response;
    }

    public static ResponseDTO FormatResponse(ResponseDTO response, String message) {
        response.setMessage(message);
        return response;
    }
}
