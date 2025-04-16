package com.wareflow.buildify.domain.admin.product.mapper;

import com.wareflow.buildify.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

// 관리자 - 상품조회 Mapepr
@Mapper
public interface AdminProductMapper {

    // 관리자 - 상품 전체 조회
    List<ProductVO> adminProductView();

    List<ProductVO> search(@Param("type") String searchType,
                           @Param("keyword") String keyword);

    // 관리자 상품 수정
    int adminProductModify(List<ProductVO> voList);

    // 관리자 상품 삭제
    int adminProductRemove (List<ProductVO> voList);

}
