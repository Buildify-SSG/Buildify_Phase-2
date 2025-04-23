package com.wareflow.buildify.domain.admin.dashboard.service;

import com.wareflow.buildify.dto.*;


import java.util.List;
import java.util.Map;

// 관리자 - 대시보드 서비스 인터페이스
public interface AdminDashboardService {

    // 입고 요청, 입고 승인
    // 차트 구성
    Map<String,Object> getInboundStats();

    // 출고 요청, 출고 승인
    // 차트 구성
    Map<String,Object> getOutboundStats();

    // 오늘의 출고 건수
    InboundStatsDTO getTodayInboundStats();

    // 오늘의 출고 현황
    OutboundStatsDTO getTodayOutboundStats();

    List<WeatherInfoDTO> getWeatherInfo();

    List<WareHouseDashBoardDTO> getWarehouseDashboardInfo();


}
