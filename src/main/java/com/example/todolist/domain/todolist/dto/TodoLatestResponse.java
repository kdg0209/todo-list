package com.example.todolist.domain.todolist.dto;

import com.example.todolist.domain.todolist.domain.Status;

import java.time.LocalDateTime;

public record TodoLatestResponse(Long id, String title, String contents, Status status, LocalDateTime createdDatetime) {

    public static TodoLatestResponse empty() {
        return new TodoLatestResponse(null, null, null, null, null);
    }
}
