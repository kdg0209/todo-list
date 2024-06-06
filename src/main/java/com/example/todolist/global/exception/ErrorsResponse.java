package com.example.todolist.global.exception;

import java.util.List;

public record ErrorsResponse(List<FieldErrors> errors) {
}

