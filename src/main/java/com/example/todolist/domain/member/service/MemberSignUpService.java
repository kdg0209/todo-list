package com.example.todolist.domain.member.service;

import com.example.todolist.domain.member.dao.port.MemberDaoPort;
import com.example.todolist.domain.member.domain.AuthorityType;
import com.example.todolist.domain.member.domain.Member;
import com.example.todolist.domain.member.dto.MemberSignUpRequest;
import com.example.todolist.domain.member.dto.MemberSignUpResponse;
import com.example.todolist.domain.member.exception.MemberException;
import com.example.todolist.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignUpService {

    private final PasswordEncoder encoder;
    private final MemberDaoPort memberDaoPort;

    public MemberSignUpResponse signUp(MemberSignUpRequest request) {
        validate(request.memberId(), request.nickName());

        var member = Member.builder()
                .memberId(request.memberId())
                .password(encodePassword(request.password()))
                .nickName(request.nickName())
                .authorityType(AuthorityType.ROLE_USER)
                .build();

        memberDaoPort.save(member);

        return new MemberSignUpResponse(member.getMemberId(), member.getNickName());
    }

    private void validate(String memberId, String nickName) {
        var hasDuplicatedMemberId = memberDaoPort.hasDuplicatedMemberId(memberId);
        if (hasDuplicatedMemberId) {
            throw new MemberException(ErrorCode.ERROR_ALREADY_REGISTERED_MEMBER_ID);
        }

        var hasDuplicatedNickName = memberDaoPort.hasDuplicatedNickName(nickName);
        if (hasDuplicatedNickName) {
            throw new MemberException(ErrorCode.ERROR_ALREADY_REGISTERED_MEMBER_NICKNAME);
        }
    }

    private String encodePassword(String password) {
        return encoder.encode(password);
    }
}
