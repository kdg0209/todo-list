package com.example.todolist.domain.member.service;

import com.example.todolist.domain.member.dto.MemberLoginRequest;
import com.example.todolist.domain.member.dto.MemberLoginResponse;
import com.example.todolist.domain.member.service.port.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberLoginService {

    private final TokenProvider tokenProvider;

    public MemberLoginResponse login(MemberLoginRequest request) {
        var token = tokenProvider.generatorToken(request.memberId(), request.password());

        return new MemberLoginResponse(token);
    }
}
