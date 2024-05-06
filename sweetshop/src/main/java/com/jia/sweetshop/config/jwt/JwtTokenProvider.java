package com.jia.sweetshop.config.jwt;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.jia.sweetshop.config.jwt.utils.JWTUtil;
import com.jia.sweetshop.config.redis.utils.RedisCache;
import com.jia.sweetshop.model.domain.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long validityInMilliseconds;

    @Autowired
    private RedisCache redisCache;

    public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", authorities)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        String userId = JWTUtil.parseToken(token).getSubject();
        String redisKey="login:" + userId;
        // 这里可以根据业务需求从数据库或其他存储中获取用户信息
        LoginUser user = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(user)){
            throw new RuntimeException("用户未登录");
        }
        //将用户信息与权限信息传入
        return new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());
    }

    public boolean validateToken(String token) {
        Claims claims = JWTUtil.parseToken(token);

        if (claims != null) {
            // 获取过期时间
            Date expiration = claims.getExpiration();
            // 验证是否过期
            if (expiration != null && expiration.before(new Date())) {
                // JWT 已过期
                System.out.println("token信息已过期");
                return false;
            } else {
                // 未过期
               return true;
            }
        } else {
            // 有效期不存在
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        // 从请求中获取 Token
        String token = request.getHeader("token");
        // 如果没有token 不解析直接放行
        if (StringUtils.isBlank(token)) {
            return null;
        }
        return token;
    }
}
