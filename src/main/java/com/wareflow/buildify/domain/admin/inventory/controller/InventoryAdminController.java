package com.wareflow.buildify.domain.admin.inventory.controller;

import com.wareflow.buildify.domain.admin.inventory.service.InventoryAdminService;
import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.dto.InventoryUpdateDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/pages/inventory")
@RequiredArgsConstructor
@Log4j2


public class InventoryAdminController {


    private final InventoryAdminService inventoryAdminService;

    private Pagination pagination;

    @GetMapping("/inventory-1")
    public String getAdminInventory(Model model, @RequestParam(defaultValue = "1") int page){
        List<InventoryDTO>inventoryList = inventoryAdminService.getAdminInventory();
        log.info(inventoryList.size());
        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");

        return "admin/layouts/adminlayout";


    }

//    @GetMapping("/inventory-1/search")
    @RequestMapping(value = "/inventory-1/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchUserInventory(InventoryFilterDTO filter, Model model, @RequestParam(defaultValue = "1") int page) {
        List<InventoryDTO> inventoryList = inventoryAdminService.searchAdminInventory(filter);
        log.info(inventoryList.size());

        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");
        return "admin/layouts/adminlayout";

    }

    @RequestMapping(value = "/inventory-1/getMidCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getMidCategories(@RequestParam("category1") String category1) {
        System.out.println("📢 Controller 들어옴, category1 = " + category1);

        List<String> midCategories = inventoryAdminService.findMidCategoriesByLevel1(category1);

        System.out.println("📢 Controller midCategories = " + midCategories);

        return midCategories;

    }

    @RequestMapping(value = "/inventory-1/getSmallCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getSmallCategories(@RequestParam("category2") String category2) {
        return inventoryAdminService.findSmallCategoriesByLevel2(category2);
    }

    // 재고 수량 수정(AJAX JSON 비동기)
    @PostMapping("/inventory-1/updateQuantity")
    @ResponseBody
    public Map<String,Object> updateQuantity(@RequestBody InventoryUpdateDTO dto) {
        boolean ok = inventoryAdminService.updateQuantity(dto.getInventoryId(), dto.getQuantity());
        return ok
                ? Map.of("success", true)
                : Map.of("success", false, "message", "업데이트 실패");
    }

    /** AJAX JSON 비동기 삭제 */
    @PostMapping("/inventory-1/delete")
    @ResponseBody
    public Map<String,Object> deleteInventories(@RequestBody List<String> inventoryIds) {
        int deleted = inventoryAdminService.deleteInventory(inventoryIds);
        return Map.of(
                "success", true,
                "deletedCount", deleted
        );
    }






}
