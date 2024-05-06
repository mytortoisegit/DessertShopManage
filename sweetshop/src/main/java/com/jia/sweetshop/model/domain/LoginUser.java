package com.jia.sweetshop.model.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.jia.sweetshop.model.bean.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 定义个用户登录信息类用户处理用户  信息以及鉴权
 */
@Data
@NoArgsConstructor
public class LoginUser  implements UserDetails {

    private SysUser sysUser;

    // 定义权限菜单信息
    private List<String> permissions;
    // 不序列化对象， 防止redis因为安全需求 报异常
    @JSONField(serialize=false)
    private List<GrantedAuthority> authorities;

    public LoginUser(SysUser sysUser, List<String> permissions) {
        this.sysUser = sysUser;
        this.permissions = permissions;
    }

    // 重写UserDetails 中权限配置方法，将用户权限信息集合放入
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (authorities != null) {
            return authorities;
        }
        authorities= permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return sysUser.getPassword();
    }

    @Override
    public String getUsername() {
        return sysUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
