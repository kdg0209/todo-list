package com.example.todolist.domain.member.service.port;

public interface TokenProvider {

    String generatorToken(String memberId, String password);
}
