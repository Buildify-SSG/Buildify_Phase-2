package com.wareflow.buildify.domain.admin.systemOperation.controller;


import com.wareflow.buildify.dto.UserDTO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// 관리자 - 창고 계약 관리 컨트롤러 구현체
public class AdminWarehouseLeaseControllerImpl implements AdminWarehouseLeaseController{

    //유저 계약정보 가져오기
    @Override
    public List<UserDTO> getUserLeaseInfo(Model model) {
        return null;
    }

    // 계약 승인
    @Override
    public List<UserDTO> approveLeaseRequests(RedirectAttributes redirectAttributes) {
        return null;
    }

    // 계약 거절
    @Override
    public List<UserDTO> rejectLeaseRequests(RedirectAttributes redirectAttributes) {
        return null;
    }

    // 계약 변경
    @Override
    public List<UserDTO> modifyLeaseRequests(RedirectAttributes redirectAttributes) {
        return null;
    }
}
