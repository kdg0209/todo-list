package com.example.todolist.infra.jwt;

import com.example.todolist.global.exception.ErrorCode;
import com.example.todolist.global.utils.MessageSourceUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 401 Unauthorized
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private static final String MESSAGE = "message";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        JSONObject json = new JSONObject();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        json.put(MESSAGE, MessageSourceUtils.getMessage(ErrorCode.ERROR_UNAUTHORIZED));
        response.getWriter().print(json);
    }
}