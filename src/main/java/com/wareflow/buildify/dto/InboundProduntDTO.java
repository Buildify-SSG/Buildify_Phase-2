package com.wareflow.buildify.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date reqInboundDate;
    private String wareId;
    private String warehousePosX;
    private String warehousePosY;
    private String inboundProcessDate;
    private String prodName;
    private int prodPrice;
    private BigDecimal prodSize;
}