package com.wareflow.buildify.domain.admin.product.controller;

import com.wareflow.buildify.domain.admin.product.service.AdminProductService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.util.Pagination;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


// 관리자-상품조회 컨트롤러 구현체
@Controller
@RequestMapping("/admin/pages/product/product-1")
public class AdminProductControllerImpl implements AdminProductController {

    private final AdminProductService adminProductService;

    public AdminProductControllerImpl(AdminProductService adminProductService) {
        this.adminProductService = adminProductService;
    }


    // 관리자 상품 전체 조회
    @Override
    @GetMapping("")
    public String adminProductView(@RequestParam(defaultValue = "1") int page,Model model) {

        List<ProductDTO> productDTOList = adminProductService.adminProductView();

        model.addAttribute("productList",productDTOList);

        Pagination.paginate(model, productDTOList, page,"/WEB-INF/views/admin/pages/product/product-1.jsp");
        return "admin/layouts/adminlayout";
    }


    // 검색
    @Override
    @GetMapping("/search")
    public String searchProduct(@RequestParam(defaultValue = "1") int page,
                                @RequestParam("searchType") String searchType,
                                @RequestParam("keyword") String keyword,
                                Model model) {

        List<ProductDTO> productDTOList = adminProductService.search(searchType,keyword);

        model.addAttribute("productList",productDTOList);

        Pagination.paginate(model, productDTOList, page,"/WEB-INF/views/admin/pages/product/product-1.jsp");
        return "admin/layouts/adminlayout";
    }

    // 관리자 상품 수정
    @Override
    @PostMapping("/api/productModify")
    public String adminProductModify(@ModelAttribute("productList") List<ProductDTO> productList,
                                     @RequestParam("selectedIndexes") List<Integer> selectedIndexes,
                                     Model model) {

        // 체크한 상품만 뽑아냄
        List<ProductDTO> selectedProducts = selectedIndexes.stream()
                .map(productList::get)
                .collect(Collectors.toList());

        adminProductService.adminProductModify(selectedProducts);
        return null;
    }

    // 관리자 상품 수정
    @Override
    @PostMapping("/api/productDelete")
    public String adminProductRemove(@RequestParam("productIds") List<Long> ids, Model model) {
        return null;
    }






}
