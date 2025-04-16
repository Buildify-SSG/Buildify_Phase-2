package com.wareflow.buildify.domain.admin.product.mapper;

import com.wareflow.buildify.vo.ProductVO;


import java.util.List;

// 관리자 - 상품조회 Mapepr
public interface AdminProductMapper {

    // 관리자 - 상품 전체 조회
    List<ProductVO> adminProductSearch();

    // 관리자 상품 수정
    List<ProductVO> adminProductModify();

    // 관리자 상품 삭제
    List<ProductVO> adminProductRemove();
}
