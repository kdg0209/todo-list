package com.example.todolist.domain.todolist.api;

import com.example.todolist.domain.todolist.dto.TodoCreateRequest;
import com.example.todolist.domain.todolist.dto.TodoCreateResponse;
import com.example.todolist.domain.todolist.service.TodoCreateService;
import com.example.todolist.infra.jwt.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoApi {

    private final TodoCreateService todoCreateService;

    @PostMapping
    public TodoCreateResponse create(@Valid @RequestBody TodoCreateRequest request) {
        var memberPrincipal = SecurityUtil.getUserPrincipal();

        return this.todoCreateService.create(memberPrincipal.memberId(), request);
    }
}
