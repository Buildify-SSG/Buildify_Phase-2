package com.wareflow.buildify.domain.admin.dashboard.service;

import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.UserDTO;

import java.util.List;

// 관리자 - 대시보드 서비스 구현체
public class AdminDashboardServiceImpl implements AdminDashboardService{

    // 입고 요청, 입고 승인
    // 대시보드 건수 표시 및 차트 구성
    @Override
    public List<InboundDTO> dashboardInbound() {
        return null;
    }

    // 출고 요청, 출고 승인
    // 대시보드 건수 표시 및 차트 구성
    @Override
    public List<OutboundDTO> dashboardOutbound() {
        return null;
    }


    // 신규가입,계약대기 건수 확인
    @Override
    public List<UserDTO> dashboardUserStatus() {
        return null;
    }
}
