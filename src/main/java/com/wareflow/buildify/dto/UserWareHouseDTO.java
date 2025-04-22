package com.wareflow.buildify.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWareHouseDTO {
    private String wareId;
    private String clientId;
    private String warehousePosX;
    private int warehousePosY;
    private BigDecimal warehouseUsage;
    private BigDecimal contractArea;
    private Date wareStartDate;
    private Date wareEndDate;
}
