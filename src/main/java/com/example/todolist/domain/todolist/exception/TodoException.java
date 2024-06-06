package com.example.todolist.domain.todolist.exception;

import com.example.todolist.global.exception.ErrorCode;
import com.example.todolist.global.utils.MessageSourceUtils;
import lombok.Getter;

@Getter
public class TodoException extends RuntimeException {

    private final ErrorCode errorCode;

    public TodoException(ErrorCode errorCode) {
        super(MessageSourceUtils.getMessage(errorCode));
        this.errorCode = errorCode;
    }

}
