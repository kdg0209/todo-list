package com.example.todolist.domain.member.service;

import com.example.todolist.config.IntegrationTest;
import com.example.todolist.domain.member.dto.MemberSignUpRequest;
import com.example.todolist.domain.member.dto.MemberSignUpResponse;
import com.example.todolist.domain.member.exception.MemberException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MemberSignUpServiceTest extends IntegrationTest {

    @Autowired
    private MemberSignUpService memberSignUpService;

    @Test
    void 정상적인_값을_사용하여_멤버를_생성할_수_있다() {

        // given
        MemberSignUpRequest request = new MemberSignUpRequest("test1", "12345", "홍길동");

        // when
        MemberSignUpResponse result = memberSignUpService.signUp(request);

        // then
        assertThat(result).isNotNull();
    }
    
    @Test
    @Sql(scripts = "/sql/members/member-duplicate-id-insert.sql")
    void 멤버_생성시_아이디가_중복되면_예외가_발생한다() {

        // given
        MemberSignUpRequest request = new MemberSignUpRequest("test", "12345", "이순신");

        // when && then
        assertThatThrownBy(() -> memberSignUpService.signUp(request))
                .isInstanceOf(MemberException.class);
    }

    @Test
    @Sql(scripts = "/sql/members/member-duplicate-nickname-insert.sql")
    void 멤버_생성시_닉네임이_중복되면_예외가_발생한다() {

        // given
        MemberSignUpRequest request = new MemberSignUpRequest("test123", "12345", "홍길동");

        // when && then
        assertThatThrownBy(() -> memberSignUpService.signUp(request))
                .isInstanceOf(MemberException.class);
    }
}