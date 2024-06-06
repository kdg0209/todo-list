package com.example.todolist.domain.todolist.dao;

import com.example.todolist.domain.todolist.domain.Todo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class TodoDao implements TodoDaoPort {

    private final JPAQueryFactory queryFactory;
    private final TodoRepository todoRepository;

    @Override
    public Todo save(Todo todo) {
        return this.todoRepository.save(todo);
    }
}
