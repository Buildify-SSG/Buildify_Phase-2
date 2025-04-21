package com.wareflow.buildify.vo;

import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InboundVO {
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
}