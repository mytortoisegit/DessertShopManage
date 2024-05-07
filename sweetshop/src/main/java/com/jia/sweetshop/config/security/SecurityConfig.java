package com.jia.sweetshop.config.security;

import com.jia.sweetshop.config.handler.AccessDeniedHandlerImpl;
import com.jia.sweetshop.config.handler.AuthenticationEntryPointImpl;
import com.jia.sweetshop.config.jwt.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Configuration
 * @EnableWebSecurity  注解表示这是一个Spring Security的配置类，并启用了Web安全功能。
 */
@Configuration
// 开启prePostEnabled 注解，在方法中使用可以 检查权限信息
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;


    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPointImpl;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;


    /**
     * 注入容器
     * 用于加密密码。使用了 Pbkdf2PasswordEncoder，也可以根据需求选择其他密码加密方式
     * 用户密码保存时需要用加密
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    // 注入容器，提供给登录业务类使用判断认证结果
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
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
        http
                //关闭 csrf 跨站攻击处理
                .csrf().disable()
                // 不通过 session获取sessionContext
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //对登录接口 .anonymous() 允许匿名访问
                .antMatchers("/api/user/login").anonymous()
                // 对首页接口登录不登录都可以访问
                .antMatchers("/api/home").permitAll()
                .antMatchers("/index").permitAll()
                // 除以上外所有接口请求都需要认证
                .anyRequest().authenticated();


        // 配置jwt过滤器
        // 到UsernamePasswordAuthenticationFilter 过滤器前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //添加认证失败处理器 添加授权失败处理器
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPointImpl).accessDeniedHandler(accessDeniedHandler);

        // 允许跨域
        http.cors();

    }


}
