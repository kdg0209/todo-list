package com.example.todolist.global.exception;

import org.springframework.validation.FieldError;

public record FieldErrors(String field, String value, String reason) {

    public FieldErrors (FieldError error) {
        this(error.getField(), error.getRejectedValue().toString(), error.getDefaultMessage());
    }
}

