package com.wareflow.buildify.domain.user.inventory.controller;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.inventory.service.InventoryUserService;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.util.ExportExcel;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/users/pages/inventory")
@RequiredArgsConstructor
@Log4j2


/**
 * 사용자용 재고 관리 컨트롤러.
 * 재고 조회, 검색, 엑셀 내보내기, 카테고리 분류 조회 기능을 제공합니다.
 */
public class InventoryUserController {


    private final InventoryUserService inventoryUserService;

//    private Pagination pagination;

    private final ExportExcel exportExcel;


//    @GetMapping("/inventory-1/api/excel")
    /**
     * 엑셀 파일로 재고 목록을 다운로드합니다.
     * 데이터가 없으면 사용자 페이지로 리다이렉트합니다.
     */
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

    /**
     * 재고 목록 페이지를 렌더링합니다. (페이징 적용)
     * @param page 보여줄 페이지 번호 (기본값 1)
     */
    @GetMapping("/inventory-1")
    public String getUserInventoryList(Model model, @RequestParam(defaultValue = "1") int page) {
        log.info("컨트롤러 진입 성공");
        // 재고 데이터 조회
        List<InventoryDTO> inventoryList = inventoryUserService.getUserInventory();
        String msg;
        if(inventoryList.isEmpty()){
            msg ="현재 재고가 없습니다.";
        }else{
            msg = "Export Excel Success";
        }
        log.info(inventoryList.size());
        model.addAttribute("msg",msg);
        // 페이징 처리 후 모델에 추가
        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/users/pages/inventory/inventory-1.jsp");

        return "users/layouts/userlayout";
    }

//    @GetMapping("/inventory-1/search")
    /**
     * 필터 조건에 따라 재고를 검색하고 페이지를 렌더링합니다.
     * @param filter 검색 조건 DTO
     * @param page   페이지 번호
     */
    @RequestMapping(value = "/inventory-1/search", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchUserInventory(InventoryFilterDTO filter,Model model, @RequestParam(defaultValue = "1") int page) {


        // 1) 로그인한 회원의 clientId 꺼내기
        String clientId = ((CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal())
                .getClientId();
        filter.setClientId(clientId);

        // 필터링된 재고 조회
        List<InventoryDTO> inventoryList = inventoryUserService.searchUserInventory(filter);
        log.info(inventoryList.size());

//        model.addAttribute("List", inventoryList);

        // 페이징 처리 후 뷰로 전달
        Pagination.paginate(model,inventoryList,page,"/WEB-INF/views/users/pages/inventory/inventory-1.jsp");
        return "users/layouts/userlayout";

    }

//    @GetMapping("/inventory-1/getMidCategories")
    /**
     * AJAX 요청: 대분류에 해당하는 중분류 목록을 JSON으로 반환합니다.
     */
    @RequestMapping(value = "/inventory-1/getMidCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getMidCategories(@RequestParam("category1") String category1) {
        System.out.println("📢 Controller 들어옴, category1 = " + category1);
        // 로그인한 사용자 clientId 조회
        String clientId = ((CustomUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())
                .getClientId();

        List<String> midCategories = inventoryUserService.findMidCategoriesByLevel1(clientId,category1);

        System.out.println("📢 Controller midCategories = " + midCategories);

        return midCategories;

    }

//    @GetMapping("/inventory-1/getSmallCategories")
    /**
     * AJAX 요청: 중분류에 해당하는 소분류 목록을 JSON으로 반환합니다.
     */
    @RequestMapping(value = "/inventory-1/getSmallCategories", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<String> getSmallCategories(@RequestParam("category2") String category2) {
        String clientId = ((CustomUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal())
                .getClientId();

        return inventoryUserService.findSmallCategoriesByLevel2(clientId,category2);
    }


}
