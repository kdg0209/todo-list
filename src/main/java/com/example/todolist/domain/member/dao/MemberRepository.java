package com.example.todolist.domain.member.dao;

import com.example.todolist.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

interface MemberRepository extends JpaRepository<Member, Long> {
}
