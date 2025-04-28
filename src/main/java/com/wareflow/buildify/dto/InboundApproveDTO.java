package com.wareflow.buildify.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@ToString
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class InboundApproveDTO {
    private String inventoryId;
    private String prodId;
    private String clientId;
    private String wareId;
    private String warePosX;
    private int warePosY;
    private String inboundId;
    private int quantity;
    private BigDecimal prodSize;
    private BigDecimal newUsage;
}
