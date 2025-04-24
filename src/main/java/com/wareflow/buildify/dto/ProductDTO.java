package com.wareflow.buildify.dto;

import com.github.ckpoint.toexcel.annotation.ExcelHeader;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    @ExcelHeader(headerName = "상품 ID", priority = 0)
    private String prodId;         // [접두어]-[날짜]-[랜덤문자열]

    @ExcelHeader(headerName = "브랜드", priority = 1)
    private String brand;

    @ExcelHeader(headerName = "상품명", priority = 2)
    private String prodName;

    @ExcelHeader(headerName = "가격", priority = 3)
    private Integer prodPrice;
    private Integer prodCode;      // 중복 x
    @ExcelHeader(headerName = "상품 사이즈", priority = 5)
    private BigDecimal prodSize;   // cm^3.3 단위

    @ExcelHeader(headerName = "카테고리 ID", priority = 4)
    private String prodCategoryid;
    private String clientId;
}