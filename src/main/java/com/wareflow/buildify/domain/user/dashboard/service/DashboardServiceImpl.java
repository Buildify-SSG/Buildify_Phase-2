package com.wareflow.buildify.domain.user.dashboard.service;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.dashboard.mapper.DashboardMapper;
import com.wareflow.buildify.domain.user.warehouse.mapper.UserWarehouseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardMapper dashboardMapper;

    @Override
    public int getDayInbound(int status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String clientId = userDetails.getClientId();
        return dashboardMapper.selectDayInbound(clientId, status);
    }

    @Override
    public int getWeekInbound(int status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String clientId = userDetails.getClientId();
        return dashboardMapper.selectWeekInbound(clientId, status);
    }

    @Override
    public int getDayOutbound(int status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String clientId = userDetails.getClientId();
        return dashboardMapper.selectDayOutbound(clientId, status);
    }

    @Override
    public int getWeekOutbound(int status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String clientId = userDetails.getClientId();
        return dashboardMapper.selectWeekOutbound(clientId, status);
    }
}
