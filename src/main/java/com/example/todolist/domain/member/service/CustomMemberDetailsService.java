package com.example.todolist.domain.member.service;

import com.example.todolist.domain.member.dao.port.MemberDaoPort;
import com.example.todolist.domain.member.domain.CustomMemberDetails;
import com.example.todolist.domain.member.domain.Member;
import com.example.todolist.domain.member.exception.MemberException;
import com.example.todolist.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomMemberDetailsService implements UserDetailsService {

    private final MemberDaoPort memberDaoPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var member = memberDaoPort.findByMemberId(username)
                .orElseThrow(() -> new MemberException(ErrorCode.ERROR_AVAILABLE_MEMBER_NOT_FOUND));

        return createMember(member);
    }

    private CustomMemberDetails createMember(Member member) {
        var grantedAuthorities = member.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityType().name()))
                .collect(Collectors.toList());

        return CustomMemberDetails.builder()
                .memberId(member.getMemberId())
                .password(member.getPassword())
                .nickName(member.getNickName())
                .authorities(grantedAuthorities)
                .build();
    }
}
