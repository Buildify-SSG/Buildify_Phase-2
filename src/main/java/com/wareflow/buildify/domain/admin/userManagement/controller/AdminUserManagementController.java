package com.wareflow.buildify.domain.admin.userManagement.controller;

import com.github.ckpoint.toexcel.core.ToWorkBook;
import com.github.ckpoint.toexcel.core.ToWorkSheet;
import com.github.ckpoint.toexcel.core.type.ToWorkBookType;
import com.wareflow.buildify.domain.admin.userManagement.service.AdminUserManagementService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.util.ExportExcel;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

// 관리자 - 회원 조회 컨트롤러 구현체
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/pages/userManagement")
public class AdminUserManagementController {

    private final AdminUserManagementService adminUserManagementService;
    private final ExportExcel exportExcel;

    // 회원 조회
    @GetMapping("/userManagement-1")
    public String  getUserInfo(@RequestParam(defaultValue = "1") int page, Model model,RedirectAttributes redirectAttributes) {
        log.info("회원 조회 시작");
        List<UserDTO> userDTOList = adminUserManagementService.getUserInfo();
        log.info("컨트롤러 DTO Size : {}",userDTOList.size());
        String msg;
        if (userDTOList.isEmpty()){
            msg = "현재 가입된 회원이 없습니다.";
        } else {
            msg = "Export Excel Success";
        }
        model.addAttribute("msg", msg);
        Pagination.paginate(model, userDTOList, page, "/WEB-INF/views/admin/pages/userManagement/userManagement-1.jsp");
        return "admin/layouts/adminlayout";
    }

    @RequestMapping(value = "/userManagement-1/search", method = {RequestMethod.GET , RequestMethod.POST})
    public String search(@RequestParam(defaultValue = "1") int page,
                         @RequestParam("searchType") String searchType,
                         @RequestParam("keyword") String keyword,
                         Model model) {

        log.debug("🔍 검색조건 - type: {}, keyword: {}", searchType, keyword);

        List<UserDTO> userDTOList = adminUserManagementService.search(searchType,keyword);
        log.debug("🔍 검색결과 수: {}", userDTOList.size());


        Pagination.paginate(model, userDTOList, page,"/WEB-INF/views/admin/pages/userManagement/userManagement-1.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/userManagement-1/api/excel")
    public String adminUserManagementExportExcel(HttpServletResponse response, Model model) {
        List<UserDTO> data = adminUserManagementService.getUserInfo();
        if (!data.isEmpty()) {
            exportExcel.exportExcel(data, response, "adminUserManagementList");
        } else {
            model.addAttribute("body","/WEB-INF/views/admin/pages/userManagement/userManagement-1.jsp");
            return "admin/layouts/adminlayout";
        }
        return null;
    }
}
