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
@RequiredArgsConstructor// final 필드에 대한 생성자 자동 주입
@Log4j2

/**
 * 관리자용 재고 관리 컨트롤러
 * 재고 조회, 검색, 엑셀 내보내기, AJAX 비동기 업데이트/삭제 기능을 제공합니다.
 */
public class InventoryAdminController {


    private final InventoryAdminService inventoryAdminService;

//    private Pagination pagination;

    private final ExportExcel exportExcel;

    /**
     * 엑셀 파일로 전체 재고 목록을 내보냅니다.
     * 엑셀 생성 후에는 뷰를 반환하지 않고, response 스트림에 직접 작성합니다.
     */
    @RequestMapping(value = "/inventory-1/api/excel", method = {RequestMethod.GET, RequestMethod.POST})
    public String inventoryAdminExportExcel(HttpServletResponse response, Model model){
        log.info("inventory export excel");
        // 서비스에서 전체 재고 조회
        List<InventoryAdminDTO> list = inventoryAdminService.getAdminInventory();
        log.info("컨트롤러 리스트 사이즈 :{}",list.size());
        int result = 0;
        if (list.size() > 0) {
            // 데이터가 있을 경우 엑셀로 내보냄
            result = exportExcel.exportExcel(list,response,"admin_inventory_list");
        }else {
            // 데이터가 없으면 사용자 화면으로 메시지 전달 후 리턴
            model.addAttribute("body","/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");
            return "admin/layouts/adminlayout";
        }

        log.info("엑셀 출력 결과 :{}",result);
        return null;
    }

    /**
     * 페이지네이션된 전체 재고 목록 페이지를 렌더링합니다.
     * 요청 시 기본 페이지 번호는 1입니다.
     */
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
    /**
     * 필터 조건에 따른 재고 검색 결과 페이지를 렌더링합니다.
     * GET/POST 요청 모두 처리합니다.
     */
    @RequestMapping(value = "/inventory-1/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchAdminInventory(InventoryFilterDTO filter, Model model, @RequestParam(defaultValue = "1") int page) {
        List<InventoryAdminDTO> inventoryList = inventoryAdminService.searchAdminInventory(filter);
        log.info(inventoryList.size());

        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");
        return "admin/layouts/adminlayout";

    }
    /**
     * AJAX 요청: 대분류(category1)에 해당하는 중분류 목록을 JSON으로 반환합니다.
     */
    @RequestMapping(value = "/inventory-1/getMidCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getMidCategories(@RequestParam("category1") String category1) {
        System.out.println("📢 Controller 들어옴, category1 = " + category1);

        List<String> midCategories = inventoryAdminService.findMidCategoriesByLevel1(category1);

        System.out.println("📢 Controller midCategories = " + midCategories);

        return midCategories;

    }

    /**
     * AJAX 요청: 중분류(category2)에 해당하는 소분류 목록을 JSON으로 반환합니다.
     */
    @RequestMapping(value = "/inventory-1/getSmallCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getSmallCategories(@RequestParam("category2") String category2) {
        return inventoryAdminService.findSmallCategoriesByLevel2(category2);
    }

    /**
     * AJAX JSON 비동기: 재고 수량을 업데이트합니다.
     * 요청 본문으로 InventoryUpdateDTO를 받고, 성공 여부를 JSON으로 반환합니다.
     */
    @PostMapping("/inventory-1/updateQuantity")
    @ResponseBody
    public Map<String,Object> updateQuantity(@RequestBody InventoryUpdateDTO dto) {
        boolean ok = inventoryAdminService.updateQuantity(dto.getInventoryId(), dto.getQuantity());
        return ok
                ? Map.of("success", true)
                : Map.of("success", false, "message", "업데이트 실패");
    }

    /**
     * AJAX JSON 비동기: 여러 재고를 삭제합니다. 삭제된 건수를 JSON으로 반환합니다.
     */
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
