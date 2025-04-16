package com.wareflow.buildify.domain.admin.product.controller;

import com.wareflow.buildify.dto.ProductDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

// 관리자 - 상품조회 컨트롤러 인터페이스
public interface AdminProductController {

    // 관리자 상품 전체 조회
    String adminProductView(int page,Model model);

    //  관리자 상품 검색
    String searchProduct(int page,String searchType,String keyword,Model model);

    // 관리자 상품 수정
    String adminProductModify(List<ProductDTO> productList,List<Integer> selectedIndexes,Model model);

    // 관리자 상품 삭제
    String adminProductRemove(List<Long> ids,Model model);
}
