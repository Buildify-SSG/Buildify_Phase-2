package com.wareflow.buildify.domain.user.inventory.controller;

import com.wareflow.buildify.domain.user.inventory.service.InventoryUserService;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/users/pages/inventory")
@RequiredArgsConstructor
@Log4j2

public class InventoryUserController {

    @Autowired
    private InventoryUserService inventoryUserService;

    private Pagination pagination;


    @GetMapping("/inventory-1")
    public String getUserInventoryList(Model model, @RequestParam(defaultValue = "1") int page) {
        List<InventoryDTO> inventoryList = inventoryUserService.getUserInventory();
        log.info(inventoryList.size());
        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/users/pages/inventory/inventory-1.jsp");

        return "users/layouts/userlayout";
    }

    @GetMapping("/inventory-1/search")
    public String searchUserInventory(InventoryFilterDTO filter,Model model, @RequestParam(defaultValue = "1") int page) {
        List<InventoryDTO> inventoryList = inventoryUserService.searchUserInventory(filter);
        log.info(inventoryList.size());

        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/users/pages/inventory/inventory-1.jsp");
        return "users/layouts/userlayout";

    }

    @GetMapping("/inventory-1/getMidCategories")
    @ResponseBody
    public List<String> getMidCategories(@RequestParam("category1") String category1) {
        System.out.println("📢 Controller 들어옴, category1 = " + category1);

        List<String> midCategories = inventoryUserService.findMidCategoriesByLevel1(category1);

        System.out.println("📢 Controller midCategories = " + midCategories);

        return midCategories;

    }

    @GetMapping("/inventory-1/getSmallCategories")
    @ResponseBody
    public List<String> getSmallCategories(@RequestParam("category2") String category2) {
        return inventoryUserService.findSmallCategoriesByLevel2(category2);
    }


}
