package com.example.todolist.domain.member.service;

import com.example.todolist.domain.member.dao.port.MemberDaoPort;
import com.example.todolist.domain.member.domain.AuthorityType;
import com.example.todolist.domain.member.domain.Member;
import com.example.todolist.domain.member.dto.MemberWithdrawResponse;
import com.example.todolist.domain.member.exception.MemberException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MemberWithdrawServiceTest {

    @InjectMocks
    private MemberWithdrawService memberWithdrawService;

    @Mock
    private MemberDaoPort memberDaoPort;

    @Test
    void 유효한_사용자가_없는데_탈퇴를_하려는_경우_예외가_발생한다() {

        // given
        var memberId = "test1";

        // when
        when(memberDaoPort.findByMemberId(anyString())).thenThrow(MemberException.class);

        // then
        assertThatThrownBy(() -> memberWithdrawService.withdraw(memberId))
                .isInstanceOf(MemberException.class);
    }

    @Test
    void 유효한_사용자가_탈퇴를_하려는_경우_변수값이_true로_변경됩니다() {

        // given
        var memberId = "test1";
        var member = Member.builder()
                .memberId("test1")
                .password("1234")
                .nickName("홍길동")
                .authorityType(AuthorityType.ROLE_USER)
                .build();

        // when
        when(memberDaoPort.findByMemberId(anyString())).thenReturn(Optional.of(member));
        MemberWithdrawResponse result = memberWithdrawService.withdraw(memberId);

        // then
        assertThat(result.isDeleted()).isTrue();
    }
}