package com.wareflow.buildify.domain.user.dashboard.mapper;

import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.OutboundVO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DashboardMapper {

    // 일간 입고 건수 (status: 0=요청, 1=승인)
    int selectDayInbound(@Param("clientId") String clientId,
                         @Param("status")   int status);

    // 주간 입고 건수
    int selectWeekInbound(@Param("clientId") String clientId,
                          @Param("status")    int status);

    // 일간 출고 건수
    int selectDayOutbound(@Param("clientId") String clientId,
                          @Param("status")    int status);

    // 주간 출고 건수
    int selectWeekOutbound(@Param("clientId") String clientId,
                           @Param("status")    int status);

}
