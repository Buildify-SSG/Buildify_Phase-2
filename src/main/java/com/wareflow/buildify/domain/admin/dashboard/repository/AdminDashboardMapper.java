package com.wareflow.buildify.domain.admin.dashboard.repository;

import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.OutboundVO;
import com.wareflow.buildify.vo.UserVO;

import java.util.List;

// 관리자 - 대시보드 Mapper
public interface AdminDashboardMapper {

    // 입고 요청, 입고 승인 Mapper
     List<InboundVO> dashboardInbound();

    // 출고 요청, 출고 승인 Mapper
    List<OutboundVO> dashboardOutbound();

    // 신규가입,계약대기 Mapper
    List<UserVO> dashboardUserStatus();
}
