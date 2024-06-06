package com.example.todolist.domain.member.exception;

import com.example.todolist.global.exception.ErrorCode;
import com.example.todolist.global.utils.MessageSourceUtils;
import lombok.Getter;

@Getter
public class MemberException extends RuntimeException {

    private final ErrorCode errorCode;

    public MemberException(ErrorCode errorCode) {
        super(MessageSourceUtils.getMessage(errorCode));
        this.errorCode = errorCode;
    }
}
