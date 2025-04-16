package com.wareflow.buildify.domain.admin.systemOperation.service;

import com.wareflow.buildify.dto.UserDTO;


import java.util.List;

// 관리자 - 창고 계약 관리 서비스 인터페이스
public interface AdminWarehouseLeaseService {

    // 유저 계약정보 가져오기
    List<UserDTO> getUserLeaseInfo();

    // 계약 승인
    List<UserDTO> approveLeaseRequests();

    // 계약 거절
    List<UserDTO> rejectLeaseRequests();

    // 계약 수정
    List<UserDTO> modifyLeaseRequests();

}
