package com.example.todolist.domain.todolist.api;

import com.example.todolist.domain.todolist.dto.TodoCreateRequest;
import com.example.todolist.domain.todolist.dto.TodoCreateResponse;
import com.example.todolist.domain.todolist.dto.TodoLatestResponse;
import com.example.todolist.domain.todolist.dto.TodoListResponse;
import com.example.todolist.domain.todolist.service.TodoCreateService;
import com.example.todolist.domain.todolist.service.TodoFindAllService;
import com.example.todolist.domain.todolist.service.TodoLatestService;
import com.example.todolist.infra.jwt.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoApi {

    private final TodoCreateService todoCreateService;
    private final TodoFindAllService todoFindAllService;
    private final TodoLatestService todoLatestService;

    @PostMapping
    public TodoCreateResponse create(@Valid @RequestBody TodoCreateRequest request) {
        var memberPrincipal = SecurityUtil.getUserPrincipal();

        return this.todoCreateService.create(memberPrincipal.memberId(), request);
    }

    @GetMapping
    public TodoListResponse findAll(@PageableDefault Pageable pageable) {
        var memberPrincipal = SecurityUtil.getUserPrincipal();
        return todoFindAllService.findAll(pageable, memberPrincipal.memberId());
    }

    @GetMapping("/latest")
    public TodoLatestResponse findLatest() {
        var memberPrincipal = SecurityUtil.getUserPrincipal();

        return this.todoLatestService.findLatest(memberPrincipal.memberId());
    }
}
