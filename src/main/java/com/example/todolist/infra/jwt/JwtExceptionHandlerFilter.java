package com.example.todolist.infra.jwt;

import com.example.todolist.global.utils.MessageSourceUtils;
import com.example.todolist.infra.jwt.exception.TokenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtExceptionHandlerFilter extends OncePerRequestFilter {

    private static final String MESSAGE = "message";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            filterChain.doFilter(request,response);
        } catch (TokenException e){
            JSONObject json = new JSONObject();
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            json.put(MESSAGE, MessageSourceUtils.getMessage(e.getErrorCode()));
            response.getWriter().print(json);
        }
    }
}
