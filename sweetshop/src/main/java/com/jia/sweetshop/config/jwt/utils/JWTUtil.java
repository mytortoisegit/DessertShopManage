package com.jia.sweetshop.config.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具
 */
public class JWTUtil {

    private static final String SECRET_KEY = "didida"; // 设置密钥，根据实际情况修改
    private static final long EXPIRATION_TIME = 60*60*1000; // 设置过期时间为1小时

    // 生成JWT
    public static String generateToken(String userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setId(UUID.randomUUID().toString()) // 设置JWT的唯一标识
                .setSubject(userId) // 设置用户标识
                .setIssuedAt(now) // 设置JWT签发时间
                .setExpiration(expiration) // 设置JWT过期时间
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // 使用HS256算法签名JWT
                .compact();
    }

    // 解析JWT
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // 测试方法
    public static void main(String[] args) {
        String userId = "user123";

        // 生成JWT
        String token = generateToken(userId);
        System.out.println("Generated Token: " + token);

        // 解析JWT
        Claims claims = parseToken(token);
        System.out.println("JWT ID: " + claims.getId());
        System.out.println("Subject (User ID): " + claims.getSubject());
        System.out.println("Issued At: " + claims.getIssuedAt());
        System.out.println("Expiration: " + claims.getExpiration());
    }
}
