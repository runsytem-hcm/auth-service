package jp.gmo.auth.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jp.gmo.auth.dto.AccountDto;

@Component
public class JwtTokenProvider {

    @Value("${jwt.base64-secret}")
    private String secret;

    @Value("${jwt.token-validity}")
    private long validity;

    public String doGenerateToken(AccountDto account, boolean rememberMe) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("user", account);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(account.getEmployeeName())
                .setExpiration(new Date(System.currentTimeMillis() + validity))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}
