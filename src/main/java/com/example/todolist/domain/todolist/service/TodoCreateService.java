package com.example.todolist.domain.todolist.service;

import com.example.todolist.domain.member.dao.port.MemberDaoPort;
import com.example.todolist.domain.member.exception.MemberException;
import com.example.todolist.domain.todolist.dao.TodoDaoPort;
import com.example.todolist.domain.todolist.domain.Todo;
import com.example.todolist.domain.todolist.dto.TodoCreateRequest;
import com.example.todolist.domain.todolist.dto.TodoCreateResponse;
import com.example.todolist.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoCreateService {

    private final MemberDaoPort memberDaoPort;
    private final TodoDaoPort todoDaoPort;

    public TodoCreateResponse create(String memberId, TodoCreateRequest request) {
        var member = memberDaoPort.findByMemberId(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.ERROR_AVAILABLE_MEMBER_NOT_FOUND));

        var todo = Todo.builder()
                .title(request.title())
                .contents(request.contents())
                .status(request.status())
                .createdDatetime(LocalDateTime.now())
                .member(member)
                .build();

        todoDaoPort.save(todo);

        return new TodoCreateResponse(todo.getId());
    }
}
