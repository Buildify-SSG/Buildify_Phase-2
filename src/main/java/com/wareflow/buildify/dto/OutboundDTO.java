package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OutboundDTO {
    private String outboundId;
    private String prodId;
    private String clientId;
    private int quantity;
    private int status;
    private Date reqOutboundDate;
    private String wareId;
    private String warehousePosX;
    private int warehousePosY;
    private Date outboundProcessDate;
    private String prodName;

}
