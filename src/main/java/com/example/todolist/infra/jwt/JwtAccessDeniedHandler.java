package com.example.todolist.infra.jwt;

import com.example.todolist.global.exception.ErrorCode;
import com.example.todolist.global.utils.MessageSourceUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 403 Forbidden
 */
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private static final String MESSAGE = "message";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        JSONObject json = new JSONObject();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        json.put(MESSAGE, MessageSourceUtils.getMessage(ErrorCode.ERROR_FORBIDDEN));
        response.getWriter().print(json);
    }
}