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
public class InventoryAdminDTO {
    @ExcelHeader(headerName = "재고ID", priority = 0)
    private String inventoryId;
    @ExcelHeader(headerName = "고객ID", priority = 1)
    private String clientId;
    @ExcelHeader(headerName = "상품ID", priority = 2)
    private String prodId;
    @ExcelHeader(headerName = "수량", priority = 3)
    private int quantity;
    @ExcelHeader(headerName = "창고ID", priority = 4)
    private String wareId;
    @ExcelHeader(headerName = "창고명", priority = 5)
    private String wareName;
    @ExcelHeader(headerName = "창고구역", priority = 6)
    private String warePosition;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelHeader(headerName = "최종입고일", priority = 7)
    private Date lastInboundDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ExcelHeader(headerName = "최종출고일", priority = 8)
    private Date lastOutboundDate;
}
