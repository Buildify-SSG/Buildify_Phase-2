package com.wareflow.buildify.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}