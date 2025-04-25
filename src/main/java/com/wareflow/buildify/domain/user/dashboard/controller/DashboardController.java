package com.wareflow.buildify.domain.user.dashboard.controller;

import com.wareflow.buildify.domain.admin.dashboard.service.AdminDashboardService;
import com.wareflow.buildify.domain.admin.dashboard.service.AdminDashboardServiceImpl;
import com.wareflow.buildify.domain.user.dashboard.mapper.DashboardMapper;
import com.wareflow.buildify.domain.user.dashboard.service.DashboardService;
import com.wareflow.buildify.dto.WareHouseDashBoardDTO;
import com.wareflow.buildify.dto.WeatherInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users/pages")
public class DashboardController {

    private final DashboardService dashboardService;
    private final AdminDashboardService adminDashboardService;

    @GetMapping("/index")
    public String getDashboard(Model model) {
        int countDayInboundRequest =  dashboardService.getDayInbound(0);
        int countDayInboundApproval =  dashboardService.getDayInbound(1);

        int countWeekInboundRequest =  dashboardService.getWeekInbound(0);
        int countWeekInboundApproval =  dashboardService.getWeekInbound(1);

        int countDayOutboundRequest =  dashboardService.getDayOutbound(0);
        int countDayOutboundApproval =  dashboardService.getDayOutbound(1);

        int countWeekOutboundRequest =  dashboardService.getWeekOutbound(0);
        int countWeekOutboundApproval =  dashboardService.getWeekOutbound(1);

        List<WeatherInfoDTO> weatherInfoDTOList = adminDashboardService.getWeatherInfo();

        model.addAttribute("countDayInboundRequest", countDayInboundRequest);
        model.addAttribute("countDayInboundApproval", countDayInboundApproval);

        model.addAttribute("countWeekInboundRequest", countWeekInboundRequest);
        model.addAttribute("countWeekInboundApproval", countWeekInboundApproval);

        model.addAttribute("countDayOutboundRequest", countDayOutboundRequest);
        model.addAttribute("countDayOutboundApproval", countDayOutboundApproval);

        model.addAttribute("countWeekOutboundRequest", countWeekOutboundRequest);
        model.addAttribute("countWeekOutboundApproval", countWeekOutboundApproval);
        model.addAttribute("weatherInfoDTOList", weatherInfoDTOList);
        model.addAttribute("body","/WEB-INF/views/users/pages/index.jsp");

        return "users/layouts/userlayout";
    }
}
