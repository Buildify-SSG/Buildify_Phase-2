package com.wareflow.buildify.domain.admin.systemOperation.mapper;

import com.wareflow.buildify.vo.UserVO;
import com.wareflow.buildify.vo.WareHouseVO;

import java.util.List;

// 관리자 - 창고 레이아웃 조회 Mapper
public interface AdminWarehouseMapper {

    //창고 레이아웃 정보 가져오기
    List<WareHouseVO> getWarehouseList();

    // 계약 승인
    List<UserVO> approveLeaseRequests();

    // 계약 거절
    List<UserVO> rejectLeaseRequests();

    // 계약 수정
    List<UserVO> modifyLeaseRequests();
}
