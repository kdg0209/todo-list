package com.example.todolist.infra.jwt.exception;

import com.example.todolist.global.exception.ErrorCode;
import com.example.todolist.global.utils.MessageSourceUtils;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

    private final ErrorCode errorCode;

    public TokenException(ErrorCode errorCode) {
        super(MessageSourceUtils.getMessage(errorCode));
        this.errorCode = errorCode;
    }
}
