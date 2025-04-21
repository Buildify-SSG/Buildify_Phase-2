package com.wareflow.buildify.domain.auth.login.security;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {

    private final String id;
    private final String password;
    private final String role;

    private String clientId;    // ✅ 추가 (User일 경우)
    private String adminNumber; // ✅ 추가 (Admin일 경우)

    @Builder
    public CustomUserDetails(String id, String password, String role,
                             String clientId, String adminNumber) {
        this.id = id;
        this.password = password;
        this.role = role;
        this.clientId = clientId;
        this.adminNumber = adminNumber;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }

    // 계정 상태 관련 설정 (true로 고정)
    @Override public boolean isAccountNonExpired()     { return true; }
    @Override public boolean isAccountNonLocked()      { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled()               { return true; }

}
