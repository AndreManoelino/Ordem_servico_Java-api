package com.andre.os.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> errors = new ArrayList<>();
    public ValidationError(long timestamp, int value, String error) {
        super();
    }
    public ValidationError(Long timestamp, String message, String errors) {
        super(timestamp,message,errors);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }
    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
    // Tratando herança e polimofirmos
}
