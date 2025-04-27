package com.wareflow.buildify.domain.user.dashboard.service;

import org.apache.ibatis.annotations.Param;

public interface DashboardService {
    // 일간 입고 건수 (status: 0=요청, 1=승인)
    int getDayInbound(int status);

    // 주간 입고 건수
    int getWeekInbound(int status);

    // 일간 출고 건수
    int getDayOutbound(int status);

    // 주간 출고 건수
    int getWeekOutbound(int status);
}
