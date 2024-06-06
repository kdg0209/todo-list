package com.example.todolist.domain.todolist.dto;

import com.example.todolist.domain.todolist.domain.Status;
import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public record TodoQueryDto(Long id, String title, String contents, Status status, LocalDateTime createdDatetime) {

    @QueryProjection
    public TodoQueryDto {

    }
}
