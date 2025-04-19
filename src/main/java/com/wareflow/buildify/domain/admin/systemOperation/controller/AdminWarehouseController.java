package com.wareflow.buildify.domain.admin.systemOperation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wareflow.buildify.domain.admin.systemOperation.service.AdminWarehouseService;
import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

// 관리자 - 창고 레이아웃 조회 컨트롤러 구현체
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("")
public class AdminWarehouseController {

    private final AdminWarehouseService adminWarehouseService;
    // 창고 레이아웃 정보 가져오기
    @GetMapping("/admin/pages/systemOperation/systemOperation-1")
    public String getWarehouseList(Model model) throws JsonProcessingException {
        Map<String , Map<String, List<WarehouseViewDTO>>> layoutmap = adminWarehouseService.getWarehouseList();

        log.info("컨트롤러 map 사이즈 : "+layoutmap.size());
        model.addAttribute("body","/WEB-INF/views/admin/pages/systemOperation/systemOperation-1.jsp");
        model.addAttribute("map", layoutmap);

        List<WareHouseDTO> wareHouseDTOList = adminWarehouseService.getWarehouseInfo();
        model.addAttribute("wareInfo", wareHouseDTOList);

        ObjectMapper mapper = new ObjectMapper();
        String mapJson = mapper.writeValueAsString(layoutmap);
        model.addAttribute("mapJson", mapJson);

        return "admin/layouts/adminlayout";
    }
}
