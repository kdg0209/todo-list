package com.example.todolist.domain.todolist.dao;

import com.example.todolist.domain.todolist.domain.Todo;

public interface TodoDaoPort {

    Todo save(Todo todo);
}
