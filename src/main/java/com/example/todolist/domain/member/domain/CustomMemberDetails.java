package com.example.todolist.domain.member.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class CustomMemberDetails implements UserDetails {

    private final String memberId;
    private final String password;
    private final String nickName;
    private final Collection<? extends GrantedAuthority> authorities;

    @Builder
    public CustomMemberDetails(String memberId, String password, String nickName, Collection<? extends GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.password = password;
        this.nickName = nickName;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nickName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
