package com.wareflow.buildify.domain.admin.product.service;

import com.wareflow.buildify.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

// 관리자 - 상품조회 서비스 인터페이스
public interface AdminProductService {

    // 관리자 상품 전체 조회
    List<ProductDTO> adminProductView();

    // 검색
    List<ProductDTO> search(String searchType,String keyword);

    // 관리자 상품 삭제
    int adminProductRemove(List<String> productDTOList);
}
