package com.example.todolist.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Comment(value = "member pk")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Comment(value = "사용자 아이디")
    @Column(name = "member_id", length = 100, nullable = false, updatable = false)
    private String memberId;

    @Comment(value = "사용자 비밀번호")
    @Column(name = "password", nullable = false)
    private String password;

    @Comment(value = "사용자 닉네임")
    @Column(name = "nick_name", length = 50, nullable = false)
    private String nickName;

    @Comment(value = "사용자 탈퇴여부")
    @Column(name = "is_delete", length = 1)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Authority> authorities = new HashSet<>();

    @Builder
    public Member(String memberId, String password, String nickName, AuthorityType authorityType) {
        this.memberId = memberId;
        this.password = password;
        this.nickName = nickName;
        this.isDeleted = false;
        this.authorities.add(new Authority(this, authorityType));
    }

    public void withdraw() {
        this.isDeleted = true;
    }
}
