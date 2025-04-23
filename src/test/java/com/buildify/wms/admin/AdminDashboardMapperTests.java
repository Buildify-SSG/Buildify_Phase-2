package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.dashboard.mapper.AdminDashboardMapper;
import com.wareflow.buildify.dto.InboundStatsDTO;
import com.wareflow.buildify.dto.OutboundStatsDTO;
import com.wareflow.buildify.dto.WareHouseDashBoardDTO;
import com.wareflow.buildify.dto.WeatherInfoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class AdminDashboardMapperTests {

    @Autowired
    AdminDashboardMapper adminDashboardMapper;

    @Test
    @DisplayName("관리자 대시보드 입고 현황 매퍼 테스트")
    public void inboundStatsTest(){
        List<InboundStatsDTO> inboundStatsDTOList = adminDashboardMapper.getInboundStats();
        log.info("입고 현황 테스트 : {}",inboundStatsDTOList.size());
        for(InboundStatsDTO inboundStatsDTO : inboundStatsDTOList){
            log.info(inboundStatsDTO);
        }
    }


    @Test
    @DisplayName("관리자 대시보드 출고 현황 매퍼 테스트")
    public void outboundStatsTest(){
        List<OutboundStatsDTO> outboundStatsDTOList = adminDashboardMapper.getOutboundStats();
        log.info("출고 현황 테스트 : {}",outboundStatsDTOList.size());
        for (OutboundStatsDTO outboundStatsDTO : outboundStatsDTOList){
            log.info(outboundStatsDTO);
        }
    }

    @Test
    @DisplayName("대시보드 창고 현황조회 테스트")
    public void warehouseDashboardTest(){
        List<WareHouseDashBoardDTO> wareHouseDashBoardDTOList = adminDashboardMapper.getWarehouseDashboardInfo();
        log.info("창고 현황 조회 테스트 : {}",wareHouseDashBoardDTOList.size());
        for (WareHouseDashBoardDTO wareHouseDashBoardDTO : wareHouseDashBoardDTOList){
            log.info(wareHouseDashBoardDTO);
        }
    }

}
