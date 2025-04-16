package com.wareflow.buildify.domain.admin.systemOperation.mapper;

import com.wareflow.buildify.vo.UserVO;

import java.util.List;

// 관리자 - 창고 계약 관리 Mapper
public interface AdminWarehouseLeaseMapper {

    // 유저 계약정보 가져오기
    List<UserVO> getUserLeaseInfo();
}
