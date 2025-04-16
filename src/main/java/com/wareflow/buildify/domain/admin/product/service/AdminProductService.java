package com.wareflow.buildify.domain.admin.product.service;

import com.wareflow.buildify.dto.ProductDTO;


import java.util.List;

// 관리자 - 상품조회 서비스 인터페이스
public interface AdminProductService {

    // 관리자 상품 전체 조회
    List<ProductDTO> adminProductSearch();

    // 관리자 상품 수정
    List<ProductDTO> adminProductModify();

    // 관리자 상품 삭제
    List<ProductDTO> adminProductRemove();
}
