package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InventoryDTO {
    private String prodId;
    private String prodName;
    private String brand;
    private String clientId;
    private String prodPrice;
    private String inventoryId;


    private String wareId;

    private int quantity;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date last_inbound_date;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date last_outbound_date;

    // 날짜 범위 검색
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date startDate;
//
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private Date endDate;


    private String categoryLevel1;
    private String categoryLevel2;
    private String categoryLevel3;

    private String sortBy; // asc / desc
}
