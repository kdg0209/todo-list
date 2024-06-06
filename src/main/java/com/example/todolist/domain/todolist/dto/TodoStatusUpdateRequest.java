package com.example.todolist.domain.todolist.dto;

import com.example.todolist.domain.todolist.domain.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TodoStatusUpdateRequest(@NotNull @Positive Long id, @NotNull Status status) {
}
