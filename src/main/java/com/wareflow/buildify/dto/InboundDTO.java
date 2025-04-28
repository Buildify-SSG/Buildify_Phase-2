package com.wareflow.buildify.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InboundDTO {
    private String inboundId;
    private String prodId;
    private String clientId;
    private int quantity;
    private int inboundStatus;
    private Date reqInboundDate;
    private String wareId;
    private String warehousePosX;
    private int warehousePosY;
    private String inboundProcessDate;
//    private List<String> prodIds;
}