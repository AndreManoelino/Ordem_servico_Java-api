package com.andre.os.services.exceptions;


import java.io.Serial;

public class ObjectNotFoundExceptions extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundExceptions(String message, Throwable cause) {
        super(message, cause);
    }
    public ObjectNotFoundExceptions(String message) {
        super(message);
    }





}
