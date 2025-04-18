package com.wareflow.buildify.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
//    private int pageNum;
//    private int amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date last_inbound_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date last_outbound_date;

    // 날짜 범위 검색
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;


//    public int getSkip() {
//        return (pageNum - 1) * amount;
//    }
}
