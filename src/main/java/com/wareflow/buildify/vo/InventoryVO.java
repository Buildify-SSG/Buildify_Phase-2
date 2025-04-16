package com.wareflow.buildify.vo;

import lombok.Data;

import java.util.Date;
@Data
public class InventoryVO {
    private String prodId;               // 상품 ID
    private String clientId;             // 입점사 ID
    private int quantity;                // 수량
    private String wareId;              // 창고 ID
    private Date lastInboundDate;       // 최종 입고일
    private Date lastOutboundDate;      // 최종 출고일
    private Double warehousePosX;       // 창고 위치 X
    private Double warehousePosY;       // 창고 위치 Y
}
