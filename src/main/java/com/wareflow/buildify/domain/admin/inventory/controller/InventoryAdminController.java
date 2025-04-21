package com.wareflow.buildify.domain.admin.inventory.controller;

import com.wareflow.buildify.domain.admin.inventory.service.InventoryAdminService;
import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/admin/pages/inventory")
@RequiredArgsConstructor
@Log4j2


public class InventoryAdminController {

    @Autowired
    private InventoryAdminService inventoryAdminService;

    private Pagination pagination;

    @GetMapping("/inventory-1")
    public String getAdminInventory(Model model, @RequestParam(defaultValue = "1") int page){
        List<InventoryDTO>inventoryList = inventoryAdminService.getAdminInventory();
        log.info(inventoryList.size());
        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");

        return "admin/layouts/adminlayout";


    }

    @GetMapping("/inventory-1/search")
    public String searchUserInventory(InventoryFilterDTO filter, Model model, @RequestParam(defaultValue = "1") int page) {
        List<InventoryDTO> inventoryList = inventoryAdminService.searchAdminInventory(filter);
        log.info(inventoryList.size());

        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");
        return "admin/layouts/adminlayout";

    }


    @GetMapping("/inventory-1/getMidCategories")
    @ResponseBody
    public List<String> getMidCategories(@RequestParam("category1") String category1) {
        System.out.println("📢 Controller 들어옴, category1 = " + category1);

        List<String> midCategories = inventoryAdminService.findMidCategoriesByLevel1(category1);

        System.out.println("📢 Controller midCategories = " + midCategories);

        return midCategories;

    }

    @GetMapping("/inventory-1/getSmallCategories")
    @ResponseBody
    public List<String> getSmallCategories(@RequestParam("category2") String category2) {
        return inventoryAdminService.findSmallCategoriesByLevel2(category2);
    }





}
