package com.wareflow.buildify.domain.admin.product.controller;

import com.wareflow.buildify.domain.admin.product.service.AdminProductService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.util.ListWrapper;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;


// 관리자-상품조회 컨트롤러 구현체
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("")
public class AdminProductController {

    private final AdminProductService adminProductService;


    // 관리자 상품 전체 조회
    @GetMapping("/admin/pages/product/product-1")
    public String adminProductView(@RequestParam(defaultValue = "1") int page,Model model) {

        List<ProductDTO> productList = adminProductService.adminProductView();

        Pagination.paginate(model, productList, page,"/WEB-INF/views/admin/pages/product/product-1.jsp");
        return "admin/layouts/adminlayout";
    }

    // 검색
    @GetMapping("/admin/pages/product/product-1/search")
    public String searchProduct(@RequestParam(defaultValue = "1") int page,
                                @RequestParam("searchType") String searchType,
                                @RequestParam("keyword") String keyword,
                                Model model) {

        List<ProductDTO> productDTOList = adminProductService.search(searchType,keyword);

        model.addAttribute("productList",productDTOList);

        Pagination.paginate(model, productDTOList, page,"/WEB-INF/views/admin/pages/product/product-1.jsp");
        return "admin/layouts/adminlayout";
    }

    // 관리자 상품 삭제
    @PostMapping("/admin/pages/product/product-1/api/productRemove")
    public String adminProductRemove(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam("selectedIndexes") List<String> selectedIndexes,
                                     RedirectAttributes redirect,
                                     Model model) {
        log.warn("🔥🔥🔥 adminProductRemove");
        log.info("컨트롤러 인덱스 : "+selectedIndexes.size());
        // 체크한 상품만 뽑아냄
//        List<ProductDTO> selectedProducts = selectedIndexes.stream()
//                .map(productList.getList()::get)
//                .collect(Collectors.toList());
        log.info(selectedIndexes.size());

        int rows = adminProductService.adminProductRemove(selectedIndexes);
        log.info(rows);
        String msg = "상품 " + rows + "개 삭제 성공하였습니다.";
        List<ProductDTO> productDTOList = adminProductService.adminProductView();

        redirect.addFlashAttribute("msg", msg);
        model.addAttribute("body","/admin/pages/product/product-1");
        return "redirect:/admin/pages/product/product-1?page=1";
//        return "admin/pages/product/product-1";
//        Pagination.paginate(model, productDTOList, page,"/WEB-INF/views/admin/pages/product/product-1.jsp");
//        return "admin/layouts/adminlayout";
    }





}
