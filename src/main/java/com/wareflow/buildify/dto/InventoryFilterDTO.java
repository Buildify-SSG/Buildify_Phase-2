package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

/**
 * 재고 조회 시 필터링 조건을 전달하기 위한 DTO 클래스입니다.
 * 대/중/소 분류, 검색 유형, 키워드, 정렬 기준, 클라이언트 ID 정보를 포함합니다.
 */
public class InventoryFilterDTO {

    private String category1;    // 대분류 카테고리명
    private String category2;    // 중분류 카테고리명
    private String category3;    // 소분류 카테고리명

    private String searchType;   // 검색 기준 (상품명, 브랜드, 상품ID 등)
    private String keyword;      // 검색어

    private String sortBy;

    private String clientId;
}
