package org.zerock.ex3.member.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@Log4j2
public class JWTUtil {
    private static String key = "123456789012345678901234567890";

    public String createToken(Map<String, Object> valueMap, int min) {
        SecretKey key = null;

        try {
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes(UTF_8));
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
        return Jwts.builder().header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .issuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .expiration((Date.from(ZonedDateTime.now()
                        .plusMinutes(min).toInstant())))
                .claims(valueMap)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public Map<String, Object> validateToken(String token) {
        SecretKey key = null;

        try {
            key = Keys.hmacShaKeyFor(JWTUtil.key.getBytes(UTF_8));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        Claims claims = Jwts.parser().verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        log.info("claims={}", claims);

        return claims;
    }

}
