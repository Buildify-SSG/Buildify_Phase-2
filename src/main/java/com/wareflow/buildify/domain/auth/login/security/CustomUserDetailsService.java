package com.wareflow.buildify.domain.auth.login.security;

import com.wareflow.buildify.domain.auth.login.mapper.AdminLoginMapper;
import com.wareflow.buildify.domain.auth.login.mapper.AuthMapper;
import com.wareflow.buildify.domain.auth.login.mapper.UserLoginMapper;
import com.wareflow.buildify.vo.AdminVO;
import com.wareflow.buildify.vo.AuthVO;
import com.wareflow.buildify.vo.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthMapper authMapper;
    private final UserLoginMapper userLoginMapper;
    private final AdminLoginMapper adminLoginMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        log.info("🟢 로그인 시도: {}", id);

        // 1️⃣ auth 테이블에서 id와 role 확인
        AuthVO authVO = authMapper.findById(id);
        if (authVO == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }

        String role = authVO.getRole();

        if(role.equals("0")){
            UserVO user = userLoginMapper.findById(id);
            if (user == null) {

                throw new UsernameNotFoundException("사용자 정보가 존재하지 않습니다.");
            }

            return CustomUserDetails.builder()
                    .id(user.getUserId())
                    .password(user.getUserPw())
                    .role("ROLE_USER")
                    .clientId(user.getClientId())
                    .build();
        } else {
            // 관리자
            AdminVO admin = adminLoginMapper.findById(id);
            if (admin == null) {
                throw new UsernameNotFoundException("관리자 정보가 존재하지 않습니다.");
            }

            return CustomUserDetails.builder()
                    .id(admin.getAdminId())
                    .password(admin.getAdminPassword())
                    .role("ROLE_ADMIN")
                    .adminNumber(admin.getAdminNumber())
                    .build();
        }

    }
}
