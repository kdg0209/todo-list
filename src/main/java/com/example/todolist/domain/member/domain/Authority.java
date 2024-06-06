package com.example.todolist.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "authority")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @Comment(value = "authority pk")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority_type", length = 50, nullable = false)
    private AuthorityType authorityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_authority_members"))
    private Member member;

    public Authority(Member member, AuthorityType authorityType) {
        this.member = member;
        this.authorityType = authorityType;
    }
}

