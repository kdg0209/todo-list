package com.example.todolist.domain.member.dao.port;

import com.example.todolist.domain.member.domain.Member;

import java.util.Optional;

public interface MemberDaoPort {

    Member save(Member member);
    Optional<Member> findByMemberId(String memberId);
    boolean hasDuplicatedMemberId(String memberId);
    boolean hasDuplicatedNickName(String nickName);
}
