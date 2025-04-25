package com.wareflow.buildify.domain.user.inventory.controller;

import com.wareflow.buildify.domain.user.inventory.service.InventoryUserService;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
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

@Controller
@RequestMapping("/users/pages/inventory")
@RequiredArgsConstructor
@Log4j2

public class InventoryUserController {


    private final InventoryUserService inventoryUserService;

    private Pagination pagination;

    private final ExportExcel exportExcel;


//    @GetMapping("/inventory-1/api/excel")
    @RequestMapping(value = "/inventory-1/api/excel", method = {RequestMethod.GET, RequestMethod.POST})
    public String inventoryExportExcel(HttpServletResponse response, Model model) {

        log.info("inventory export excel");
        List<InventoryDTO> list = inventoryUserService.getUserInventory();
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
    public String getUserInventoryList(Model model, @RequestParam(defaultValue = "1") int page) {
        log.info("컨트롤러 진입 성공");
        List<InventoryDTO> inventoryList = inventoryUserService.getUserInventory();
        String msg;
        if(inventoryList.isEmpty()){
            msg ="현재 재고가 없습니다.";
        }else{
            msg = "Export Excel Success";
        }
        log.info(inventoryList.size());
        model.addAttribute("msg",msg);
        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/users/pages/inventory/inventory-1.jsp");

        return "users/layouts/userlayout";
    }

//    @GetMapping("/inventory-1/search")
    @RequestMapping(value = "/inventory-1/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchUserInventory(InventoryFilterDTO filter,Model model, @RequestParam(defaultValue = "1") int page) {
        List<InventoryDTO> inventoryList = inventoryUserService.searchUserInventory(filter);
        log.info(inventoryList.size());

//        model.addAttribute("List", inventoryList);

        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/users/pages/inventory/inventory-1.jsp");
        return "users/layouts/userlayout";

    }

//    @GetMapping("/inventory-1/getMidCategories")
    @RequestMapping(value = "/inventory-1/getMidCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getMidCategories(@RequestParam("category1") String category1) {
        System.out.println("📢 Controller 들어옴, category1 = " + category1);

        List<String> midCategories = inventoryUserService.findMidCategoriesByLevel1(category1);

        System.out.println("📢 Controller midCategories = " + midCategories);

        return midCategories;

    }

//    @GetMapping("/inventory-1/getSmallCategories")
    @RequestMapping(value = "/inventory-1/getSmallCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getSmallCategories(@RequestParam("category2") String category2) {

        return inventoryUserService.findSmallCategoriesByLevel2(category2);
    }


}
