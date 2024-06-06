package com.example.todolist.domain.member.api;

import com.example.todolist.domain.member.dto.*;
import com.example.todolist.domain.member.service.MemberLoginService;
import com.example.todolist.domain.member.service.MemberSignUpService;
import com.example.todolist.domain.member.service.MemberWithdrawService;
import com.example.todolist.infra.jwt.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberSignUpService memberSignUpService;
    private final MemberLoginService memberLoginService;
    private final MemberWithdrawService memberWithdrawService;

    @PostMapping("/sign-up")
    public MemberSignUpResponse signUp(@Valid @RequestBody MemberSignUpRequest request) {
        return this.memberSignUpService.signUp(request);
    }

    @PostMapping("/login")
    public MemberLoginResponse login(@Valid @RequestBody MemberLoginRequest request) {
        return this.memberLoginService.login(request);
    }

    @PostMapping("/withdraw")
    public MemberWithdrawResponse withdraw() {
        var memberPrincipal = SecurityUtil.getUserPrincipal();

        return this.memberWithdrawService.withdraw(memberPrincipal.memberId());
    }
}
