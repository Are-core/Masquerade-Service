package com.masquerade.exception;

public class EntityRequestException extends Exception {

    private static final String CODE_NOT_FOUND = "CODE_NOT_FOUND";
    private static final String DOESNT_EXISTS = "The line %s doesn't exist";

    EntityRequestException(String code, String message) {}

    public static EntityRequestException doesntExists(Long id) {
        String message = String.format(DOESNT_EXISTS, id);
        return new EntityRequestException(CODE_NOT_FOUND, message);
    }

    public static EntityRequestException doesntExists(Long id, Long secondId) {
        String message = String.format(DOESNT_EXISTS, id + " - " + secondId);
        return new EntityRequestException(CODE_NOT_FOUND, message);
    }
}
