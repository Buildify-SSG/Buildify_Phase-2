package com.wareflow.buildify.domain.admin.userManagement.mapper;

import com.wareflow.buildify.vo.UserVO;

import java.util.List;

// 관리자 - 회원 조회 Mapper
public interface AdminUserManagementMapper {

    // 회원 조회
    List<UserVO> getUserInfo();
}
