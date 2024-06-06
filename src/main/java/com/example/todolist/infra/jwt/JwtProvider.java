package com.example.todolist.infra.jwt;

import com.example.todolist.domain.member.domain.CustomMemberDetails;
import com.example.todolist.domain.member.service.port.TokenProvider;
import com.example.todolist.global.exception.ErrorCode;
import com.example.todolist.infra.jwt.exception.TokenException;
import com.example.todolist.infra.jwt.util.SecurityMemberPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider implements TokenProvider {

    private static final long ACCESS_EXPIRE_TIME = (60 * 60 * 1000L) * 2; // 2시간
    private static final String MEMBER_ID = "sub";
    private static final String ROLES = "roles";

    private final Key key;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public JwtProvider(@Value("${jwt.key}") String jwtKey, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        var keyBytes = Decoders.BASE64.decode(jwtKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String generatorToken(String memberId, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(memberId, password);
        var authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        return this.createToken(authentication);
    }

    private String createToken(Authentication authentication) {
        var authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        CustomMemberDetails memberDetails = null;
        if (authentication.getPrincipal() instanceof CustomMemberDetails) {
            memberDetails = (CustomMemberDetails) authentication.getPrincipal();
        }

        // 토큰의 expire 시간을 설정
        var now = (new Date()).getTime();
        var validity = new Date(now + ACCESS_EXPIRE_TIME);

        return Jwts.builder()
                .setSubject(memberDetails.getMemberId())
                .claim(ROLES, authorities) // payload
                .signWith(SignatureAlgorithm.HS512, this.key)
                .setExpiration(validity)
                .compact();
    }

    // 토큰으로 클레임을 만들고 이를 이용해 유저 객체를 만들어서 최종적으로 authentication 객체를 리턴
    public Authentication getAuthentication(String token) {
        var claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        var authorities = Arrays.stream(claims.get(ROLES).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        var principal = new SecurityMemberPrincipal(claims.get(MEMBER_ID).toString());

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw new TokenException(ErrorCode.ERROR_INVALID_TOKEN_SIGNATURE);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            throw new TokenException(ErrorCode.ERROR_EXPIRED_TOKEN);
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw new TokenException(ErrorCode.ERROR_UNSUPPORTED_TOKEN);
        }
    }
}
