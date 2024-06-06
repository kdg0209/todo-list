package com.example.todolist.domain.todolist.domain;

import com.example.todolist.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Table(name = "todos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Todo {

    @Id
    @Comment(value = "todo pk")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Comment(value = "제목")
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Comment(value = "내용")
    @Column(name = "contents", nullable = false)
    private String contents;

    @Comment(value = "상태")
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", foreignKey = @ForeignKey(name = "fk_todos_members"))
    private Member member;

    @Builder
    public Todo(String title, String contents, Status status, Member member) {
        this.title = title;
        this.contents = contents;
        this.status = status;
        this.member = member;
    }
}
