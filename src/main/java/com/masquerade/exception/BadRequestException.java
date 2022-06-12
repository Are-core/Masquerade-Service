package com.masquerade.exception;

public class BadRequestException extends Exception{
    private static final String CODE_MISSING_PARAMETER = "PARAMETER_NOT_FOUND";
    public static final String TEXT = "Missing parameter %s";

    private BadRequestException(String code, String message) {
    }

    public static BadRequestException missingParameter() {
        String message = String.format(TEXT, "id");
        return new BadRequestException(CODE_MISSING_PARAMETER, message);
    }

    public static BadRequestException missingBody() {
        String message = String.format(TEXT, "body");
        return new BadRequestException(CODE_MISSING_PARAMETER, message);
    }
}
