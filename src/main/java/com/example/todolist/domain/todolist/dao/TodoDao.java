package com.example.todolist.domain.todolist.dao;

import com.example.todolist.domain.todolist.domain.Todo;
import com.example.todolist.domain.todolist.dto.QTodoLatestQueryDto;
import com.example.todolist.domain.todolist.dto.TodoLatestQueryDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.todolist.domain.todolist.domain.QTodo.todo;

@Repository
@RequiredArgsConstructor
class TodoDao implements TodoDaoPort {

    private final JPAQueryFactory queryFactory;
    private final TodoRepository todoRepository;

    @Override
    public Todo save(Todo todo) {
        return this.todoRepository.save(todo);
    }

    @Override
    public Optional<TodoLatestQueryDto> findLatestByMemberId(String memberId) {
        var result = queryFactory
                .select(new QTodoLatestQueryDto(
                        todo.id,
                        todo.title,
                        todo.contents,
                        todo.status,
                        todo.createdDatetime
                ))
                .from(todo)
                .where(todo.member.memberId.eq(memberId))
                .orderBy(todo.createdDatetime.desc())
                .fetchFirst();

        return Optional.ofNullable(result);
    }
}
