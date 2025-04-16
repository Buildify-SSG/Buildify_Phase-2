package com.wareflow.buildify.domain.admin.systemOperation.service;

import com.wareflow.buildify.dto.UserDTO;

import java.util.List;

// 관리자 - 창고 계약 관리 서비스 구현체
public class AdminWarehouseLeaseServiceImpl implements AdminWarehouseLeaseService {

    // 유저 계약정보 가져오기
    @Override
    public List<UserDTO> getUserLeaseInfo() {
        return null;
    }

    // 계약 승인
    @Override
    public List<UserDTO> approveLeaseRequests() {
        return null;
    }

    // 계약 거절
    @Override
    public List<UserDTO> rejectLeaseRequests() {
        return null;
    }

    // 계약 변경
    @Override
    public List<UserDTO> modifyLeaseRequests() {
        return null;
    }
}
