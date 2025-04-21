package com.wareflow.buildify.dto;

import lombok.*;

import java.util.Date;
@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundProduntDTO {
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