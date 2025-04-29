package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.dashboard.mapper.AdminDashboardMapper;
import com.wareflow.buildify.domain.admin.dashboard.service.AdminDashboardService;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class AdminDashboardServiceTests {

    @Autowired
    AdminDashboardMapper adminDashboardMapper;

    @Autowired
    AdminDashboardService adminDashboardService;

    @Test
    @Transactional
    @DisplayName("관리자 입고현황 서비스 테스트")
    public void inboundStatsServiceTest(){
        InboundStatsDTO adminDashboardServiceInboundStats = adminDashboardService.getTodayInboundStats();
        String today = LocalDate.now().toString();
        log.info("관리자 입고 현황 : {}",adminDashboardServiceInboundStats);
    }

    @Test
    @Transactional
    @DisplayName("관리자 출고현황 서비스 테스트")
    public void OutboundStatsServiceTest(){
        OutboundStatsDTO adminDashboardServiceOutboundStats = adminDashboardService.getTodayOutboundStats();
        String today = LocalDate.now().toString();
        log.info("관리자 출고 현황 : {}",adminDashboardServiceOutboundStats);
    }

    @Test
    @Transactional
    @DisplayName("관리자 대시보드 날씨정보 서비스 테스트")
    public void weatherServiceTest(){
        List<WeatherInfoDTO> weatherInfoDTOList = adminDashboardService.getWeatherInfo();
        log.info("날씨 정보 불러오기 테스트 : {}",weatherInfoDTOList.size());
        for (WeatherInfoDTO weatherInfoDTO : weatherInfoDTOList){
            log.info(weatherInfoDTO);
        }
    }

    @Test
    @Transactional
    @DisplayName("관리지 대시보드 창고정보 서비스 테스트")
    public void warehouseInfoTest(){
        List<WareHouseDashBoardDTO> wareHouseDashBoardDTOList = adminDashboardService.getWarehouseDashboardInfo();
        log.info("창고 정보 불러오기 테스트 : {}",wareHouseDashBoardDTOList.size());
        for (WareHouseDashBoardDTO weatherInfoDTO : wareHouseDashBoardDTOList){
            log.info(weatherInfoDTO);
        }
    }
}
