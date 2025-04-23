package com.wareflow.buildify.domain.admin.dashboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mysql.cj.xdevapi.JsonArray;
import com.wareflow.buildify.domain.admin.dashboard.service.AdminDashboardService;
import com.wareflow.buildify.dto.*;
import com.wareflow.buildify.util.WeatherUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

// 관리자 - 대시보드 컨트롤러 구현체
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/pages/index")
public class AdminDashboardController{

    private final AdminDashboardService adminDashboardService;

    // 입고 요청, 입고 승인
    // 대시보드 건수 표시 및 차트 구성
    // MODEL로 JSP에 토스
    @GetMapping("")
    public String getDashboardStats(Model model){
        Map<String,Object> inboundStatsDTOList = adminDashboardService.getInboundStats();
        Map<String,Object> outboundStatsDTOList = adminDashboardService.getOutboundStats();
        InboundStatsDTO todayInboundStats = adminDashboardService.getTodayInboundStats();
        OutboundStatsDTO todayOutboundStats = adminDashboardService.getTodayOutboundStats();
        List<WeatherInfoDTO> weatherInfoDTOList = adminDashboardService.getWeatherInfo();
        List<WareHouseDashBoardDTO> wareHouseDashBoardDTOList = adminDashboardService.getWarehouseDashboardInfo();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // yyyy-MM-dd 포맷 유지
        try {
            String inboundChartData = objectMapper.writeValueAsString(inboundStatsDTOList);
            String outboundChartData = objectMapper.writeValueAsString(outboundStatsDTOList);
            model.addAttribute("inboundChartData", inboundChartData);
            model.addAttribute("outboundChartData", outboundChartData);

            log.info("inboundChartData json: {}", inboundChartData);
            log.info("outboundChartData json: {}", outboundChartData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        model.addAttribute("wareHouseDashBoardDTOList", wareHouseDashBoardDTOList);
        model.addAttribute("weatherInfoDTOList", weatherInfoDTOList);
        model.addAttribute("todayInboundStat", todayInboundStats);
        model.addAttribute("todayOutboundStat", todayOutboundStats);
        model.addAttribute("body", "/WEB-INF/views/admin/pages/index.jsp");
        return "admin/layouts/adminlayout";
    }

}
