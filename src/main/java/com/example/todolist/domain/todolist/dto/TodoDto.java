package com.example.todolist.domain.todolist.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TodoDto(Long id, String title, String contents, String status, LocalDateTime createdDatetime) {
}
