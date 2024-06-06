package com.example.todolist.domain.todolist.dto;

import java.util.List;

public record TodoListResponse(List<TodoDto> todos) {
}
