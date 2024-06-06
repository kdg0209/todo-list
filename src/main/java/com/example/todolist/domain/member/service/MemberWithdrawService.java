package com.example.todolist.domain.member.service;

import com.example.todolist.domain.member.dao.port.MemberDaoPort;
import com.example.todolist.domain.member.dto.MemberWithdrawResponse;
import com.example.todolist.domain.member.exception.MemberException;
import com.example.todolist.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberWithdrawService {

    private final MemberDaoPort memberDaoPort;

    public MemberWithdrawResponse withdraw(String memberId) {
        var member = memberDaoPort.findByMemberId(memberId)
                .orElseThrow(() -> new MemberException(ErrorCode.ERROR_AVAILABLE_MEMBER_NOT_FOUND));

        member.withdraw();

        return new MemberWithdrawResponse(member.getIsDeleted());
    }
}
