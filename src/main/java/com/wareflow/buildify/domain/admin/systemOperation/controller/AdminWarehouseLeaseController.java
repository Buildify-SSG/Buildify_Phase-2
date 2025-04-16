package com.wareflow.buildify.domain.admin.systemOperation.controller;

import com.wareflow.buildify.dto.UserDTO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// 관리자 - 창고 계약 관리 컨트롤러 인터페이스
public interface AdminWarehouseLeaseController {

    // 유저 계약 정보 가져오기
    List<UserDTO> getUserLeaseInfo(Model model);

    // 계약 승인
    List<UserDTO> approveLeaseRequests(RedirectAttributes redirectAttributes);

    // 계약 거절
    List<UserDTO> rejectLeaseRequests(RedirectAttributes redirectAttributes);

    // 계약 수정
    List<UserDTO> modifyLeaseRequests(RedirectAttributes redirectAttributes);
}
