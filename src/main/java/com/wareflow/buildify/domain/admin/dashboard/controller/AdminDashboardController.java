package com.wareflow.buildify.domain.admin.dashboard.controller;

import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.UserDTO;
import org.springframework.ui.Model;

import java.util.List;

// 관리자 - 대시보드 컨트롤러 인터페이스
public interface AdminDashboardController {

    // 입고 요청, 입고 승인
    // 대시보드 건수 표시 및 차트 구성
    // MODEL로 JSP에 토스
    List<InboundDTO> dashboardInbound(Model model);

    // 출고 요청, 출고 승인
    // 대시보드 건수 표시 및 차트 구성
    // MODEL로 JSP에 토스
    List<OutboundDTO> dashboardOutbound(Model model);

    // 신규가입,계약대기 건수 확인
    // return 리스트 size
    List<UserDTO> dashboardUserStatus(Model model);
}
