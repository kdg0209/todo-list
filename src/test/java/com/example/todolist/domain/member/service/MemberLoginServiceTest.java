package com.example.todolist.domain.member.service;

import com.example.todolist.config.IntegrationTest;
import com.example.todolist.domain.member.dto.MemberLoginRequest;
import com.example.todolist.domain.member.dto.MemberLoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberLoginServiceTest extends IntegrationTest {

    @Autowired
    private MemberLoginService memberLoginService;

    @Test
    @Sql(scripts = "/sql/members/default-member-insert.sql")
    void 유효한_사용자_계정을_사용하여_로그인하면_토큰을_발급받을_수_있습니다() {

        // given
        MemberLoginRequest request = new MemberLoginRequest("test", "1234");

        // when
        MemberLoginResponse result = memberLoginService.login(request);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    @Sql(scripts = "/sql/members/default-member-insert.sql")
    void 아이디가_다른_계정을_사용하여_로그인하면_예외가_발생합니다() {

        // given
        MemberLoginRequest request = new MemberLoginRequest("test1234", "1234");

        // when && then
        assertThatThrownBy(() -> memberLoginService.login(request))
                .isInstanceOf(InternalAuthenticationServiceException.class);
    }

    @Test
    @Sql(scripts = "/sql/members/default-member-insert.sql")
    void 비밀번호가_다른_계정을_사용하여_로그인하면_예외가_발생합니다() {

        // given
        MemberLoginRequest request = new MemberLoginRequest("test", "000000");

        // when && then
        assertThatThrownBy(() -> memberLoginService.login(request))
                .isInstanceOf(BadCredentialsException.class);
    }
}