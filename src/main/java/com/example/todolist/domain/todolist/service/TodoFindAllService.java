package com.example.todolist.domain.todolist.service;

import com.example.todolist.domain.todolist.dao.TodoDaoPort;
import com.example.todolist.domain.todolist.dto.TodoDto;
import com.example.todolist.domain.todolist.dto.TodoListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoFindAllService {

    private final TodoDaoPort todoDaoPort;

    public TodoListResponse findAll(Pageable pageable, String memberId) {
        var todos = todoDaoPort.findAll(pageable, memberId);

        var result = todos.stream()
                .map(dto -> TodoDto.builder()
                        .id(dto.id())
                        .title(dto.title())
                        .contents(dto.contents())
                        .status(dto.status().getDescription())
                        .createdDatetime(dto.createdDatetime())
                        .build())
                .collect(Collectors.toList());

        return new TodoListResponse(result);
    }
}
