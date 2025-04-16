package com.wareflow.buildify.domain.admin.userManagement.controller;

import com.wareflow.buildify.dto.UserDTO;
import org.springframework.ui.Model;

import java.util.List;

// 관리자 - 회원 조회 컨트롤러 인터페이스
public interface AdminUserManagementController {

    // 회원 조회
    List<UserDTO> getUserInfo(Model model);
}
