package com.example.todolist.domain.todolist.dto;

import com.example.todolist.domain.todolist.domain.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TodoCreateRequest(@NotBlank String title, @NotBlank String contents, @NotNull Status status) {
}
