package com.wareflow.buildify.common.controller;

import com.wareflow.buildify.common.service.CommonService;
import com.wareflow.buildify.domain.admin.userManagement.service.AdminUserManagementService;
import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.dto.AdminDTO;
import com.wareflow.buildify.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Log4j2
@RequiredArgsConstructor
public class CommonController {

    private final AdminUserManagementService adminUserManagementService;
    private final CommonService commonService;


    @ModelAttribute("loginUser")
    public UserDTO loginUser(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        UserDTO dto = new UserDTO();
        List<UserDTO> userDTOList = adminUserManagementService.getUserInfo();
        for (UserDTO userDTO : userDTOList) {
            if (userDTO.getClientId().equals(userDetails.getClientId())) {
                dto.setUserId(userDTO.getUserId());
                dto.setUserName(userDTO.getUserName());
                dto.setUserEmail(userDTO.getUserEmail());
                dto.setUserPhone(userDTO.getUserPhone());
                break;
            }
        }

        log.info("✅ 로그인 유저 정보: {}", dto.getUserName());
        return dto;
    }

    @ModelAttribute("loginAdmin")
    public AdminDTO loginAdmin(HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            return null;
        }

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        AdminDTO dto = new AdminDTO();
        List<AdminDTO> adminDTOList = commonService.getAdminInfo();
        for (AdminDTO admin : adminDTOList) {
            if (admin.getAdminNumber().equals(userDetails.getAdminNumber())) {
                dto.setAdminNumber(admin.getAdminNumber());
                dto.setAdminName(admin.getAdminName());
                dto.setAdminEmail(admin.getAdminEmail());
                dto.setAdminPhone(admin.getAdminPhone());
                break;
            }
        }
        log.info("✅ 로그인 Admin 정보: {}", dto.getAdminName());
        return dto;
    }

}
