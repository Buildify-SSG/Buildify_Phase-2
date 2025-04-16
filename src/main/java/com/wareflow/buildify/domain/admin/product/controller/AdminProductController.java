package com.wareflow.buildify.domain.admin.product.controller;

import com.wareflow.buildify.dto.ProductDTO;
import org.springframework.ui.Model;

import java.util.List;

// 관리자 - 상품조회 컨트롤러 인터페이스
public interface AdminProductController {

    // 관리자 상품 전체 조회
    List<ProductDTO> adminProductSearch(Model model);

    // 관리자 상품 수정
    List<ProductDTO> adminProductModify(Model model);

    // 관리자 상품 삭제
    List<ProductDTO> adminProductRemove(Model model);
}
