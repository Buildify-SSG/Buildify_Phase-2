package com.wareflow.buildify.domain.admin.inventory.controller;

import com.wareflow.buildify.domain.admin.inventory.service.InventoryAdminService;
import com.wareflow.buildify.dto.*;
import com.wareflow.buildify.util.ExportExcel;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/pages/inventory")
@RequiredArgsConstructor
@Log4j2


public class InventoryAdminController {


    private final InventoryAdminService inventoryAdminService;

    private Pagination pagination;

    private final ExportExcel exportExcel;

    @RequestMapping(value = "/inventory-1/api/excel", method = {RequestMethod.GET, RequestMethod.POST})
    public String inventoryAdminExportExcel(HttpServletResponse response, Model model){
        log.info("inventory export excel");
        List<InventoryAdminDTO> list = inventoryAdminService.getAdminInventory();
        log.info("컨트롤러 리스트 사이즈 :{}",list.size());
        int result = 0;
        if (list.size() > 0) {
            result = exportExcel.exportExcel(list,response,"user_inventory_list");
        }else {
            model.addAttribute("body","/WEB-INF/views/users/pages/inventory/inventory-1.jsp");
            return "users/layouts/userlayout";
        }

        log.info("엑셀 출력 결과 :{}",result);
        return null;
    }

    @GetMapping("/inventory-1")
    public String getAdminInventory(Model model, @RequestParam(defaultValue = "1") int page){
        List<InventoryAdminDTO>inventoryList = inventoryAdminService.getAdminInventory();
        String msg;
        if(inventoryList.isEmpty()){
            msg ="현재 재고가 없습니다.";
        }else{
            msg = "Export Excel Success";
        }
        log.info(inventoryList.size());
        model.addAttribute("msg",msg);

        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");

        return "admin/layouts/adminlayout";


    }

//    @GetMapping("/inventory-1/search")
    @RequestMapping(value = "/inventory-1/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchAdminInventory(InventoryFilterDTO filter, Model model, @RequestParam(defaultValue = "1") int page) {
        List<InventoryAdminDTO> inventoryList = inventoryAdminService.searchAdminInventory(filter);
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
