package com.example.todolist.domain.todolist.domain;

public enum Status {

    TODO("할일"),
    IN_PROGRESS("진행 중"),
    DONE("완료"),
    PENDING("대기");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
