package com.wareflow.buildify.dto;

import com.github.ckpoint.toexcel.annotation.ExcelHeader;
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
    @ExcelHeader(headerName = "상품명", priority = 1)
    private String prodName;
    @ExcelHeader(headerName = "브랜드", priority = 0)
    private String brand;
    private String clientId;
    @ExcelHeader(headerName = "가격", priority = 2)
    private String prodPrice;
    private String inventoryId;
    @ExcelHeader(headerName = "창고명", priority = 4)
    private String wareName;
    @ExcelHeader(headerName = "주소", priority = 5)
    private String wareAddress;
    private String warePosition;
    private String wareHousePosX;
    private int wareHousePosY;

    private String wareId;

    @ExcelHeader(headerName = "수량", priority = 3)
    private int quantity;


    @ExcelHeader(headerName = "최종입고일", priority = 6)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastInboundDate;


    @ExcelHeader(headerName = "최종출고일", priority = 7)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastOutboundDate;


}
