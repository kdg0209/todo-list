package com.example.todolist.domain.todolist.service;

import com.example.todolist.domain.todolist.dao.TodoDaoPort;
import com.example.todolist.domain.todolist.dto.TodoLatestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoLatestService {

    private final TodoDaoPort todoDaoPort;

    public TodoLatestResponse findLatest(String memberId) {
        var latestTodo = todoDaoPort.findLatestByMemberId(memberId);

        return latestTodo
                .map(dto -> new TodoLatestResponse(dto.id(), dto.title(), dto.contents(), dto.status(), dto.createdDatetime()))
                .orElseGet(TodoLatestResponse::empty);
    }
}
