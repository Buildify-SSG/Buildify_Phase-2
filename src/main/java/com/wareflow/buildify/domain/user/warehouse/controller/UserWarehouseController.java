package com.wareflow.buildify.domain.user.warehouse.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wareflow.buildify.domain.admin.systemOperation.service.AdminWarehouseService;
import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/users/pages")
public class UserWarehouseController {

    private final AdminWarehouseService adminWarehouseService;

    @GetMapping("/userWarehouse/userWarehouse-1")
    public String getWarehouseList(Model model) throws JsonProcessingException {
        Map<String , Map<String, List<WarehouseViewDTO>>> layoutmap = adminWarehouseService.getWarehouseList();
        log.info("layout toString() :" + layoutmap.toString());
        log.info("컨트롤러 map 사이즈 : "+layoutmap.size());
        model.addAttribute("body","/WEB-INF/views/users/pages/userWarehouse/userWarehouse-1.jsp");
        model.addAttribute("map", layoutmap);

        List<WareHouseDTO> wareHouseDTOList = adminWarehouseService.getWarehouseInfo();
        log.info("wareHouseDTOList toString() :" + wareHouseDTOList.toString());
        model.addAttribute("wareInfo", wareHouseDTOList);

        ObjectMapper mapper = new ObjectMapper();
        String mapJson = mapper.writeValueAsString(layoutmap);
        model.addAttribute("mapJson", mapJson);

        return "users/layouts/userlayout";
    }
}
