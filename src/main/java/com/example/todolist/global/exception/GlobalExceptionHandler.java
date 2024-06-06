package com.example.todolist.global.exception;

import com.example.todolist.domain.member.exception.MemberException;
import com.example.todolist.domain.todolist.exception.TodoException;
import com.example.todolist.global.utils.MessageSourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(MemberException.class)
    public ResponseEntity<ErrorResponse> handleException(MemberException exception) {
        var message = MessageSourceUtils.getMessage(exception.getErrorCode());
        return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<ErrorResponse> handleException(TodoException exception) {
        var message = MessageSourceUtils.getMessage(exception.getErrorCode());
        return new ResponseEntity<>(new ErrorResponse(message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorsResponse> handleException(BindingResult bindingResult) {
        var errors = bindingResult.getFieldErrors();
        var fieldErrors = errors.stream()
                .map(FieldErrors::new)
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorsResponse(fieldErrors), HttpStatus.BAD_REQUEST);
    }
}