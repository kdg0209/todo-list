package com.example.todolist.domain.todolist.service;

import com.example.todolist.domain.member.dao.port.MemberDaoPort;
import com.example.todolist.domain.member.domain.AuthorityType;
import com.example.todolist.domain.member.domain.Member;
import com.example.todolist.domain.member.exception.MemberException;
import com.example.todolist.domain.todolist.dao.TodoDaoPort;
import com.example.todolist.domain.todolist.domain.Status;
import com.example.todolist.domain.todolist.domain.Todo;
import com.example.todolist.domain.todolist.dto.TodoCreateRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoCreateServiceTest {

    @InjectMocks
    private TodoCreateService todoCreateService;

    @Mock
    private MemberDaoPort memberDaoPort;

    @Mock
    private TodoDaoPort todoDaoPort;

    @Test
    void 유효한_사용자는_todo를_작성할_수_있다() {

        // given
        String memberId = "test1";
        TodoCreateRequest request = new TodoCreateRequest("title", "contents", Status.TODO);

        when(memberDaoPort.findByMemberId(anyString())).thenReturn(returnsMember());
        when(todoDaoPort.save(any())).thenReturn(createTodo());

        // when
        todoCreateService.create(memberId, request);

        // then
        verify(memberDaoPort, times(1)).findByMemberId(anyString());
        verify(todoDaoPort, times(1)).save(any());
    }

    @Test
    void 유효한_사용자가_아난데_todo를_작성하는_경우_예외가_발생합니다() {

        // given
        String memberId = "test1";
        TodoCreateRequest request = new TodoCreateRequest("title", "contents", Status.TODO);

        when(memberDaoPort.findByMemberId(anyString())).thenThrow(MemberException.class);

        // when && then
        assertThatThrownBy(() -> todoCreateService.create(memberId, request))
                .isInstanceOf(MemberException.class);

        verify(todoDaoPort, never()).save(any());
    }

    private Optional<Member> returnsMember() {
        var result = Member.builder()
                .memberId("test1")
                .password("1234")
                .nickName("홍길동")
                .authorityType(AuthorityType.ROLE_USER)
                .build();

        return Optional.of(result);
    }

    private Todo createTodo() {
        return Todo.builder()
                .title("title")
                .contents("contents")
                .status(Status.TODO)
                .member(returnsMember().get())
                .build();
    }
}