package com.example.todolist.domain.member.dao;

import com.example.todolist.domain.member.dao.port.MemberDaoPort;
import com.example.todolist.domain.member.domain.Member;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.todolist.domain.member.domain.QMember.member;

@Repository
@RequiredArgsConstructor
class MemberDao implements MemberDaoPort {

    private final JPAQueryFactory queryFactory;
    private final MemberRepository memberRepository;

    @Override
    public Member save(Member member) {
        return this.memberRepository.save(member);
    }

    @Override
    public Optional<Member> findByMemberId(String memberId) {
        var result = queryFactory
                .selectFrom(member)
                .where(member.memberId.eq(memberId))
                .fetchFirst();

        return Optional.ofNullable(result);
    }

    @Override
    public boolean hasDuplicatedMemberId(String memberId) {
        var result = this.queryFactory
                .selectOne()
                .from(member)
                .where(member.memberId.eq(memberId))
                .fetchFirst();

        return result != null;
    }

    @Override
    public boolean hasDuplicatedNickName(String nickName) {
        var result = this.queryFactory
                .selectOne()
                .from(member)
                .where(member.nickName.eq(nickName))
                .fetchFirst();

        return result != null;
    }
}
