package com.example.todolist.domain.todolist.dao;

import com.example.todolist.domain.todolist.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

interface TodoRepository extends JpaRepository<Todo, Long> {
}
