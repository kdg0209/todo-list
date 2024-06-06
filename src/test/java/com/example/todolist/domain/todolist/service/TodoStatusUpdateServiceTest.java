package com.example.todolist.domain.todolist.service;

import com.example.todolist.domain.todolist.dao.TodoDaoPort;
import com.example.todolist.domain.todolist.domain.Status;
import com.example.todolist.domain.todolist.domain.Todo;
import com.example.todolist.domain.todolist.dto.TodoStatusUpdateRequest;
import com.example.todolist.domain.todolist.dto.TodoStatusUpdateResponse;
import com.example.todolist.domain.todolist.exception.TodoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoStatusUpdateServiceTest {

    @InjectMocks
    private TodoStatusUpdateService todoStatusUpdateService;

    @Mock
    private TodoDaoPort todoDaoPort;

    @Test
    void 유효한_사용자의_todo가_없으면_예외가_발생한다() {

        // given
        Long todoId = 1L;
        String memberId = "user1";
        TodoStatusUpdateRequest request = new TodoStatusUpdateRequest(todoId, Status.PENDING);

        when(todoDaoPort.findById(todoId, memberId)).thenThrow(TodoException.class);

        // when && then
        assertThatThrownBy(() -> todoStatusUpdateService.statusUpdate(memberId, request))
                .isInstanceOf(TodoException.class);
    }

    @Test
    void 유효한_사용자의_todo가_있고_상태가_진행중이랴면_대기상태로_변경할_수_있다() {

        // given
        Long todoId = 1L;
        String memberId = "user1";
        Todo todo = createTodo();

        TodoStatusUpdateRequest request = new TodoStatusUpdateRequest(todoId, Status.PENDING);

        when(todoDaoPort.findById(todoId, memberId)).thenReturn(Optional.of(todo));

        // when
        TodoStatusUpdateResponse result = todoStatusUpdateService.statusUpdate(memberId, request);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void 유효한_사용자의_todo가_있고_상태가_대가_상태라면_어떤_상태로든_변경할_수_있다() {

        // given
        Long todoId = 1L;
        String memberId = "user1";
        Todo todo = createTodo();

        TodoStatusUpdateRequest request = new TodoStatusUpdateRequest(todoId, Status.DONE);

        when(todoDaoPort.findById(todoId, memberId)).thenReturn(Optional.of(todo));

        // when
        TodoStatusUpdateResponse result = todoStatusUpdateService.statusUpdate(memberId, request);

        // then
        assertThat(result).isNotNull();
    }


    private Todo createTodo() {
        return Todo.builder()
                .status(Status.IN_PROGRESS)
                .build();
    }
}