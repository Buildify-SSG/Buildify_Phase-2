package com.wareflow.buildify.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductVO {
    private String prodId;         // [접두어]-[날짜]-[랜덤문자열]
    private String brand;
    private String prodName;
    private Integer prodPrice;
    private Integer prodCode;      // 중복 x
    private BigDecimal prodSize;   // cm^3.3 단위
    private String prodCategoryid;
    private String clientId;
}
