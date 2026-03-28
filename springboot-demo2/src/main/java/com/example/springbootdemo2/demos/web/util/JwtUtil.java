package com.example.springbootdemo2.demos.web.util;
import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {
    // 密钥 (必须至少 32 字符，否则 HS256 会报错)
    private static final String SECRET_KEY = "this-is-a-very-long-secret-key-for-hs256-at-least-32-chars!";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1小时

    // 生成密钥对象
    private static SecretKey getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    // --- 生成 Token
    public static String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)           // 注意是 setSubject
                .setIssuedAt(new Date())        // 注意是 setIssuedAt
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // 注意是 setExpiration
                .signWith(SignatureAlgorithm.HS256, getSigningKey()) // 注意这里传算法和密钥
                .compact();
    }

    // --- 解析 Token
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey()) // 注意是 setSigningKey
                .parseClaimsJws(token)          // 注意是 parseClaimsJws
                .getBody();

        return claims.getSubject();
    }

    // --- 校验 Token
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            // 过期、签名错误等都会抛这个异常
            return false;
        }
    }
}