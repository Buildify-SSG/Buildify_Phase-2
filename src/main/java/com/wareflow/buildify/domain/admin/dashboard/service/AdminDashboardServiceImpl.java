package com.wareflow.buildify.domain.admin.dashboard.service;

import com.wareflow.buildify.cache.InboundStatsListCache;
import com.wareflow.buildify.cache.OutboundStatsListCache;
import com.wareflow.buildify.cache.WarehouseDashboardCache;
import com.wareflow.buildify.cache.WeatherCache;
import com.wareflow.buildify.domain.admin.dashboard.mapper.AdminDashboardMapper;
import com.wareflow.buildify.dto.*;
import com.wareflow.buildify.util.WeatherUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 관리자 - 대시보드 서비스 구현체
@Service
@Log4j2
@RequiredArgsConstructor
public class AdminDashboardServiceImpl implements AdminDashboardService {

    private final AdminDashboardMapper adminDashboardMapper;
    private final WeatherUtil weatherUtil;


    @PostConstruct // 서버 시작시 관리자 대시보드 캐시 갱신
    public void initDashboardCache() {
        preloadDashboardCache();
        weatherCacheReload();
        wareInfoReload();
    }

    @Scheduled(cron = "0 0 6,14 * * *") // adminDashBoard 는 오전 6시, 오후 2시에 갱신
    private void preloadDashboardCache() {
        List<InboundStatsDTO> inboundStatsDTOList = adminDashboardMapper.getInboundStats();
        InboundStatsListCache.getInstance().setInboundStatsDTOList(inboundStatsDTOList);
        log.info("관리자 입고 대시보드 캐시 갱신 완료");
        log.info("입고 캐시 갱신 건수 : {}",inboundStatsDTOList.size());
        List<OutboundStatsDTO> outboundStatsDTOList = adminDashboardMapper.getOutboundStats();
        OutboundStatsListCache.getInstance().setOutboundStatsDTOList(outboundStatsDTOList);
        log.info("관리자 출고 대시보드 캐시 갱신 완료");
        log.info("출고 캐시 갱신 건수 : {}",outboundStatsDTOList.size());
    }

    @Scheduled(cron = "0 0 * * * *") // 날씨는 매시 정각 갱신
    private void weatherCacheReload(){
        List<WeatherInfoDTO> weatherCaches = weatherUtil.fetchAllCityWeather();
        WeatherCache.getInstance().setWeatherInfoCache(weatherCaches);
        log.info("날짜 정보 캐시 갱신 완료");
    }

    @Scheduled(cron = "0 0 * * * *") // 창고 현황은 매시 정각 갱신
    private void wareInfoReload(){
        List<WareHouseDashBoardDTO> wareHouseDashBoardDTOList = adminDashboardMapper.getWarehouseDashboardInfo();
        WarehouseDashboardCache.getInstance().setWareHouseDashBoardCache(wareHouseDashBoardDTOList);
        log.info("대시보드 창고 계약률/가용률 갱신 완료");
    }

    // 입고 요청, 입고 승인
    // 차트 구성
    @Override
    public Map<String,Object> getInboundStats() {
        List<InboundStatsDTO> inboundStatsDTOList = InboundStatsListCache.getInstance().getInboundStatsDTOList();
        if (inboundStatsDTOList == null || inboundStatsDTOList.isEmpty()){
            InboundStatsListCache.getInstance().setInboundStatsDTOList(adminDashboardMapper.getInboundStats());
            inboundStatsDTOList = InboundStatsListCache.getInstance().getInboundStatsDTOList();
        }
        InboundStatsListCache.getInstance().setInboundStatsDTOList(inboundStatsDTOList);

        Map<String, Object> result = new HashMap<>();
        result.put("date", inboundStatsDTOList.stream().map(InboundStatsDTO::getDate).toList());
        result.put("requestCount", inboundStatsDTOList.stream().map(InboundStatsDTO::getRequestCount).toList());
        result.put("approvalCount", inboundStatsDTOList.stream().map(InboundStatsDTO::getApprovalCount).toList());

        return result;
    }

    // 출고 요청, 출고 승인
    // 차트 구성
    @Override
    public Map<String,Object> getOutboundStats() {
        List<OutboundStatsDTO> outboundStatsLists = OutboundStatsListCache.getInstance().getOutboundStatsDTOList();
        if (outboundStatsLists == null || outboundStatsLists.isEmpty()){
            OutboundStatsListCache.getInstance().setOutboundStatsDTOList(adminDashboardMapper.getOutboundStats());
            outboundStatsLists = OutboundStatsListCache.getInstance().getOutboundStatsDTOList();
        }
        OutboundStatsListCache.getInstance().setOutboundStatsDTOList(outboundStatsLists);

        Map<String, Object> result = new HashMap<>();
        result.put("date", outboundStatsLists.stream().map(OutboundStatsDTO::getDate).toList());
        result.put("requestCount", outboundStatsLists.stream().map(OutboundStatsDTO::getRequestCount).toList());
        result.put("approvalCount", outboundStatsLists.stream().map(OutboundStatsDTO::getApprovalCount).toList());


        return result;
    }

    @Override
    public InboundStatsDTO getTodayInboundStats() {

        List<InboundStatsDTO> inboundStatsDTOList = InboundStatsListCache.getInstance().getInboundStatsDTOList();
        if (inboundStatsDTOList == null || inboundStatsDTOList.isEmpty()){
            inboundStatsDTOList = adminDashboardMapper.getInboundStats();
            InboundStatsListCache.getInstance().setInboundStatsDTOList(inboundStatsDTOList);
        }
        InboundStatsDTO todayInboundStats = new InboundStatsDTO();
        for (InboundStatsDTO inboundStatsDTO : inboundStatsDTOList){
            if (inboundStatsDTO.getDate().equals(LocalDate.now())){
                todayInboundStats.setDate(inboundStatsDTO.getDate());
                todayInboundStats.setRequestCount(inboundStatsDTO.getRequestCount());
                todayInboundStats.setApprovalCount(inboundStatsDTO.getApprovalCount());
            }
        }
        return todayInboundStats;
    }

    @Override
    public OutboundStatsDTO getTodayOutboundStats() {

        List<OutboundStatsDTO> outboundStatsDTOList = OutboundStatsListCache.getInstance().getOutboundStatsDTOList();
        if (outboundStatsDTOList == null || outboundStatsDTOList.isEmpty()){
            outboundStatsDTOList = adminDashboardMapper.getOutboundStats();
            OutboundStatsListCache.getInstance().setOutboundStatsDTOList(outboundStatsDTOList);
        }
        OutboundStatsDTO todayOutboundStats = new OutboundStatsDTO();
        for (OutboundStatsDTO outboundStatsDTO : outboundStatsDTOList){
            if (outboundStatsDTO.getDate().equals(LocalDate.now())){
                todayOutboundStats.setDate(outboundStatsDTO.getDate());
                todayOutboundStats.setRequestCount(outboundStatsDTO.getRequestCount());
                todayOutboundStats.setApprovalCount(outboundStatsDTO.getApprovalCount());
            }
        }
        return todayOutboundStats;
    }

    @Override
    public List<WeatherInfoDTO> getWeatherInfo() {

        List<WeatherInfoDTO> weatherInfoDTOList = WeatherCache.getInstance().getWeatherInfoCache();


        if (weatherInfoDTOList==null || weatherInfoDTOList.isEmpty()){
            weatherInfoDTOList = weatherUtil.fetchAllCityWeather();
            WeatherCache.getInstance().setWeatherInfoCache(weatherInfoDTOList);
        }

        return WeatherCache.getInstance().getWeatherInfoCache();
    }

    @Override
    public List<WareHouseDashBoardDTO> getWarehouseDashboardInfo() {

        List<WareHouseDashBoardDTO> wareHouseDashBoardDTOList = WarehouseDashboardCache.getInstance().getWareHouseDashBoardCache();
        if (wareHouseDashBoardDTOList==null || wareHouseDashBoardDTOList.isEmpty()){
            wareHouseDashBoardDTOList = adminDashboardMapper.getWarehouseDashboardInfo();
            WarehouseDashboardCache.getInstance().setWareHouseDashBoardCache(wareHouseDashBoardDTOList);
        }

        return WarehouseDashboardCache.getInstance().getWareHouseDashBoardCache();
    }


}
