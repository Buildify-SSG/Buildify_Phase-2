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
    public UserDetails loadUserByUsername(String id) {
        log.info("🟢 로그인 시도: {}", id);

        // 1️⃣ auth 테이블에서 id와 role 확인
        AuthVO authVO = authMapper.findById(id);
        if (authVO == null) {
            log.info("존재하지 않는 아이디입니다.");
        }

        String role = authVO.getRole();

        if(role.equals("0")){
            UserVO user = userLoginMapper.findById(id);

            if (user == null) {
               log.info("사용자 정보가 존재하지 않습니다.");
            }

            log.info("🔍 user.getClientId() = {}", user.getClientId());

            CustomUserDetails customUser = new CustomUserDetails();
            customUser.setId(user.getUserId());
            customUser.setPassword(user.getUserPw());
            customUser.setRole("ROLE_USER");
            customUser.setClientId(user.getClientId());
            return customUser;
        } else {
            // 관리자
            AdminVO admin = adminLoginMapper.findById(id);

            log.info("🔐 loaded admin pw: {}", admin.getAdminPassword());
            log.info("🟢 유저 클라이언트 아이디: {}", admin.getAdminNumber());

            if (admin == null) {
                log.info("관리자 정보가 존재하지 않습니다.");
            }


            CustomUserDetails customUser = new CustomUserDetails();
            customUser.setId(admin.getAdminId());
            customUser.setPassword(admin.getAdminPassword());
            customUser.setRole("ROLE_ADMIN");
            customUser.setAdminNumber(admin.getAdminNumber());
            return customUser;
        }

    }
}
