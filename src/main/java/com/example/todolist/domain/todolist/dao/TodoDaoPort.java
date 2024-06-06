package com.example.todolist.domain.todolist.dao;

import com.example.todolist.domain.todolist.domain.Todo;
import com.example.todolist.domain.todolist.dto.TodoLatestQueryDto;
import com.example.todolist.domain.todolist.dto.TodoQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TodoDaoPort {

    Todo save(Todo todo);
    Page<TodoQueryDto> findAll(Pageable pageable, String memberId);
    Optional<TodoLatestQueryDto> findLatestByMemberId(String memberId);
}
