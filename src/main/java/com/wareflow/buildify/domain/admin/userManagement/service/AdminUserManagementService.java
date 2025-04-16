package com.wareflow.buildify.domain.admin.userManagement.service;

import com.wareflow.buildify.dto.UserDTO;

import java.util.List;

// 관리자 - 회원 조회 서비스 인터페이스
public interface AdminUserManagementService {

    // 회원 조회
    List<UserDTO> getUserInfo();
}
