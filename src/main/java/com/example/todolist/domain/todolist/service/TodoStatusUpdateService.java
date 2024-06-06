package com.example.todolist.domain.todolist.service;

import com.example.todolist.domain.todolist.dao.TodoDaoPort;
import com.example.todolist.domain.todolist.dto.TodoStatusUpdateRequest;
import com.example.todolist.domain.todolist.dto.TodoStatusUpdateResponse;
import com.example.todolist.domain.todolist.exception.TodoException;
import com.example.todolist.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TodoStatusUpdateService {

    private final TodoDaoPort todoDaoPort;

    public TodoStatusUpdateResponse statusUpdate(String memberId, TodoStatusUpdateRequest request) {
        var todo = todoDaoPort.findById(request.id(), memberId)
                .orElseThrow(() -> new TodoException(ErrorCode.ERROR_AVAILABLE_TODO_NOT_FOUND));

        todo.updateStatus(request.status());

        return new TodoStatusUpdateResponse(todo.getId(), todo.getStatusDescription());
    }
}
