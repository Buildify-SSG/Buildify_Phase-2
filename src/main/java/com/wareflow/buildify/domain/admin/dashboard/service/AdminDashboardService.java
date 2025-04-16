package com.wareflow.buildify.domain.admin.dashboard.service;

import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.UserDTO;


import java.util.List;

// 관리자 - 대시보드 서비스 인터페이스
public interface AdminDashboardService {

    // 입고 요청, 입고 승인
    // 대시보드 건수 표시 및 차트 구성
    List<InboundDTO> dashboardInbound();

    // 출고 요청, 출고 승인
    // 대시보드 건수 표시 및 차트 구성
    List<OutboundDTO> dashboardOutbound();

    // 신규가입,계약대기 건수 확인
    List<UserDTO> dashboardUserStatus();

}
