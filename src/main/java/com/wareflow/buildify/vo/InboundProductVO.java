package com.wareflow.buildify.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class InboundProductVO {
    private String inboundId;
    private String prodId;
    private String clientId;
    private int quantity;
    private int inboundStatus;
    private Date reqInboundDate;
    private String wareId;
    private String warehousePosX;
    private String warehousePosY;
    private String inboundProcessDate;
    private String prodName;
    private int prodPrice;
    private BigDecimal prodSize;
}