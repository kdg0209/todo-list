package com.example.todolist.global.utils;

import com.example.todolist.global.exception.ErrorCode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageSourceUtils {

    @Autowired
    private MessageSource source;

    static MessageSource messageSource;

    @PostConstruct
    public void initialize() {
        messageSource = source;
    }

    public static String getMessage(ErrorCode errorCode) {
        return messageSource.getMessage(errorCode.getErrorCode(), null, LocaleContextHolder.getLocale());
    }
}
