package com.wareflow.buildify.domain.admin.systemOperation.controller;


import com.wareflow.buildify.domain.admin.systemOperation.service.AdminWarehouseLeaseService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.dto.WarehouseLeaseDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

// 관리자 - 창고 계약 관리 컨트롤러 구현체
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("")
public class AdminWarehouseLeaseController{

    private final AdminWarehouseLeaseService adminWarehouseLeaseService;

    //유저 계약정보 가져오기
    @GetMapping("/admin/pages/systemOperation/systemOperation-2")
    public String getUserLeaseInfo(@RequestParam(defaultValue = "1") int page, Model model) {
        log.info("계약 조회 시작");
        List<WarehouseLeaseDTO> warehouseLeaseDTOList = adminWarehouseLeaseService.getUserLeaseInfo();
        log.info("컨트롤러 DTO Size : {}",warehouseLeaseDTOList.size());

        Pagination.paginate(model, warehouseLeaseDTOList, page, "/WEB-INF/views/admin/pages/systemOperation/systemOperation-2.jsp");
        return "admin/layouts/adminlayout";
    }



    // 계약 변경
    @PostMapping("/admin/pages/systemOperation/systemOperation-2/api/modify")
    public String modifyLeaseRequests(
            @RequestParam("clientIdList") List<String> clientIds,
            @RequestParam("endDateList") List<String> endDates,
            @RequestParam("wareIdList") List<String> wareIds,
            @RequestParam("wareCoordList") List<String> wareCoords,
            RedirectAttributes redirect, Model model) {
        log.warn("🔥🔥🔥 adminProductRemove");

        int rows = adminWarehouseLeaseService.modifyLeaseRequests(clientIds, endDates, wareIds, wareCoords);
        log.info(rows);
        String msg = "상품 " + rows + "개 수정 성공하였습니다.";

        redirect.addFlashAttribute("msg", msg);
        model.addAttribute("body","/admin/pages/systemOperation/systemOperation-2");
        return "redirect:/admin/pages/systemOperation/systemOperation-2";
    }


    @PostMapping("/admin/pages/systemOperation/systemOperation-2/search")
    public String searchProduct(@RequestParam(defaultValue = "1") int page,
                                @RequestParam("searchType") String searchType,
                                @RequestParam("keyword") String keyword,
                                Model model) {

        log.debug("🔍 검색조건 - type: {}, keyword: {}", searchType, keyword);

        List<WarehouseLeaseDTO> warehouseLeaseDTOList = adminWarehouseLeaseService.search(searchType,keyword);
        log.debug("🔍 검색결과 수: {}", warehouseLeaseDTOList.size());

        model.addAttribute("warehouseList",warehouseLeaseDTOList);

        Pagination.paginate(model, warehouseLeaseDTOList, page,"/WEB-INF/views/admin/pages/systemOperation/systemOperation-2.jsp");
        return "admin/layouts/adminlayout";
    }
}
