package com.example.todolist.domain.todolist.dao;

import com.example.todolist.domain.todolist.domain.Todo;
import com.example.todolist.domain.todolist.dto.TodoLatestQueryDto;

import java.util.Optional;

public interface TodoDaoPort {

    Todo save(Todo todo);
    Optional<TodoLatestQueryDto> findLatestByMemberId(String memberId);
}
