package com.example.todolist.domain.todolist.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class TodoTest {

    @Test
    void 현재_상태가_진행중이라면_대기상태로만_변경할_수_있다() {

        // given
        Todo todo = Todo.builder()
                .status(Status.IN_PROGRESS)
                .build();

        // when
        todo.updateStatus(Status.PENDING);

        // then
        assertThat(todo.getStatus()).isEqualTo(Status.PENDING);
    }

    @EnumSource(value = Status.class, names = {"TODO", "IN_PROGRESS", "DONE"})
    @ParameterizedTest
    void 현재_상태가_진행중_상태라면_대기_상태를_제외한_다른_상태로_변경할_수_없다(Status status) {

        // given
        Todo todo = Todo.builder()
                .status(Status.IN_PROGRESS)
                .build();

        // when
        todo.updateStatus(status);

        // then
        assertThat(todo.getStatus()).isEqualTo(Status.IN_PROGRESS);
    }

    @EnumSource(value = Status.class, names = {"TODO", "IN_PROGRESS", "DONE", "PENDING"})
    @ParameterizedTest
    void 현재_상태가_대기_상태라면_어떤_상태로든_변경할_수_있다(Status status) {

        // given
        Todo todo = Todo.builder()
                .status(Status.PENDING)
                .build();

        // when
        todo.updateStatus(status);

        // then
        assertThat(todo.getStatus()).isEqualTo(status);
    }

    @EnumSource(value = Status.class, names = {"TODO", "IN_PROGRESS", "PENDING"})
    @ParameterizedTest
    void 현재_상태가_완료_상태라면_다른_상태로_변경될_수_없다(Status status) {

        // given
        Todo todo = Todo.builder()
                .status(Status.DONE)
                .build();

        // when
        todo.updateStatus(status);

        // then
        assertThat(todo.getStatus()).isEqualTo(Status.DONE);
    }
}