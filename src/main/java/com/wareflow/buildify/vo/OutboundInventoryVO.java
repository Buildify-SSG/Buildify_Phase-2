package com.wareflow.buildify.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class OutboundInventoryVO {
    private String inventoryId;
    private String prodId;               // 상품 ID
    private String clientId;             // 입점사 ID
    private int quantity;                // 수량
    private String wareId;              // 창고 ID
    private Date lastInboundDate;       // 최종 입고일
    private Date lastOutboundDate;      // 최종 출고일
    private String warehousePosX;       // 창고 위치 X
    private int warehousePosY;// 창고 위치 Y
    private String prodName;
    private BigDecimal prodSize;
    protected int prodPrice;
    private String outboundId;
    private int status;
}
