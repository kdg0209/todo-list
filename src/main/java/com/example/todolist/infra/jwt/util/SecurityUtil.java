package com.example.todolist.infra.jwt.util;

import com.example.todolist.global.exception.ErrorCode;
import com.example.todolist.infra.jwt.exception.TokenException;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    private SecurityUtil() { }

    public static SecurityMemberPrincipal getUserPrincipal() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new TokenException(ErrorCode.ERROR_INVALID_TOKEN_SIGNATURE);
        }

        if (authentication.getPrincipal() instanceof SecurityMemberPrincipal) {
            var principal = (SecurityMemberPrincipal) authentication.getPrincipal();
            return new SecurityMemberPrincipal(principal.memberId());
        }

        throw new TokenException(ErrorCode.ERROR_INVALID_TOKEN_SIGNATURE);
    }
}
