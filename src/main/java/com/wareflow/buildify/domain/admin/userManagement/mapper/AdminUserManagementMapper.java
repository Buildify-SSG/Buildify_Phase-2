package com.wareflow.buildify.domain.admin.userManagement.mapper;

import com.wareflow.buildify.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

// 관리자 - 회원 조회 Mapper
@Mapper
public interface AdminUserManagementMapper {

    // 회원 조회
    List<UserVO> getUserInfo();

    List<UserVO> search(@Param("type") String searchType,
                        @Param("keyword") String keyword);
}
