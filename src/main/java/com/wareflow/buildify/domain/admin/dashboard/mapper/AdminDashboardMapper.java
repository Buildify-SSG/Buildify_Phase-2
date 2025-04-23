package com.wareflow.buildify.domain.admin.dashboard.mapper;

import com.wareflow.buildify.dto.InboundStatsDTO;
import com.wareflow.buildify.dto.OutboundStatsDTO;
import com.wareflow.buildify.dto.WareHouseDashBoardDTO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.OutboundVO;
import com.wareflow.buildify.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 관리자 - 대시보드 Mapper
@Mapper
public interface AdminDashboardMapper {

    // 입고 요청, 입고 승인 Mapper
    List<InboundStatsDTO> getInboundStats();

    // 출고 요청, 출고 승인 Mapper
    List<OutboundStatsDTO> getOutboundStats();

    List<WareHouseDashBoardDTO> getWarehouseDashboardInfo();

}
