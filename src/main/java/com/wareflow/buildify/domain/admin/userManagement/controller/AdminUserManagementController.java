package com.wareflow.buildify.domain.admin.userManagement.controller;

import com.wareflow.buildify.domain.admin.userManagement.service.AdminUserManagementService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 관리자 - 회원 조회 컨트롤러 구현체
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("")
public class AdminUserManagementController {

    private final AdminUserManagementService adminUserManagementService;

    // 회원 조회
    @GetMapping("/admin/pages/userManagement/userManagement-1")
    public String  getUserInfo(@RequestParam(defaultValue = "1") int page, Model model) {
        log.info("회원 조회 시작");
        List<UserDTO> userDTOList = adminUserManagementService.getUserInfo();
        log.info("컨트롤러 DTO Size : {}",userDTOList.size());

        Pagination.paginate(model, userDTOList, page, "/WEB-INF/views/admin/pages/userManagement/userManagement-1.jsp");
        return "admin/layouts/adminlayout";
    }

    @PostMapping("/admin/pages/userManagement/userManagement-1/search")
    public String search(@RequestParam(defaultValue = "1") int page,
                         @RequestParam("searchType") String searchType,
                         @RequestParam("keyword") String keyword,
                         Model model) {

        log.debug("🔍 검색조건 - type: {}, keyword: {}", searchType, keyword);

        List<UserDTO> userDTOList = adminUserManagementService.search(searchType,keyword);
        log.debug("🔍 검색결과 수: {}", userDTOList.size());

//        model.addAttribute("userDTOList",userDTOList);

        Pagination.paginate(model, userDTOList, page,"/WEB-INF/views/admin/pages/userManagement/userManagement-1.jsp");
        return "admin/layouts/adminlayout";
    }

}
