package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InventoryFilterDTO {

    private String category1;    // 대분류 카테고리명
    private String category2;    // 중분류 카테고리명
    private String category3;    // 소분류 카테고리명

    private String searchType;   // 검색 기준 (상품명, 브랜드, 상품ID 등)
    private String keyword;      // 검색어

    private String sortBy;

    private String clientId;
}
