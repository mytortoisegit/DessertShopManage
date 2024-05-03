package com.jia.sweetshop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * @Configuration
 * @EnableWebSecurity  注解表示这是一个Spring Security的配置类，并启用了Web安全功能。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 认证
     * 用于配置用户的认证方式。这里使用了一个自定义的 UserDetailsService 来获取用户信息，并指定了密码的加密方式。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
        ;
    }

    /**
     * 授权
     * 定义了针对HTTP请求的安全规则。
     * 配置了不同URL路径的访问权限要求（例如需要 ADMIN 或 USER 角色，或者允许公开访问），以及定义了登录和注销的行为。
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
            .antMatchers("/materials/**").hasAnyRole("ADMIN", "USER")
            .antMatchers("/public/**").permitAll()
            .and().formLogin()
            .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
            .and().csrf().disable(); // Disabling CSRF for simplicity in this example
    }


    /**
     * 用于加密密码。使用了 Pbkdf2PasswordEncoder，也可以根据需求选择其他密码加密方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder(); // Use PBKDF2 encryption for password encoding
    }

    public static void main(String[] args) {
        Pbkdf2PasswordEncoder pbkd = new Pbkdf2PasswordEncoder();
//        String encodedPassword = pbkd.encode("123456","12".getBytes());
//        String encodedPassword = pbkd.encode("rk5g");
//        String encodedPassword = pbkd.encode("UJ");
//        String encodedPassword = pbkd.encode("yV9N");
//        String encodedPassword = pbkd.encode("FeKla");
//        String encodedPassword = pbkd.encode("pURV");
//        String encodedPassword = pbkd.encode("r5tiz");
//        String encodedPassword = pbkd.encode("sAVt8");

//        System.out.println(encodedPassword);
    }
}
