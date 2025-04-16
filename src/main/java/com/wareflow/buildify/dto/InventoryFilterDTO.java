package com.wareflow.buildify.dto;

import lombok.Builder;
import lombok.Data;

// 재고 조건 조회를 담는 DTO
@Data
@Builder
public class InventoryFilterDTO {
    private String wareId;
    private String clientId;
    private String categoryLevel1;
    private String categoryLevel2;
    private String categoryLevel3;
    private String prodName;
    private String sortBy;
    private int pageNum;
    private int amount;


    public int getSkip() {
        return (pageNum - 1) * amount;
    }
}
