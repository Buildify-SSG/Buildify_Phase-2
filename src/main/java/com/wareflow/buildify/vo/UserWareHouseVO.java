package com.wareflow.buildify.vo;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class UserWareHouseVO {
    private String wareId;
    private String clientId;
    private String warehousePosX;
    private int warehousePosY;
    private BigDecimal warehouseUsage;
    private BigDecimal contractArea;
    private LocalDate wareStartDate;
    private LocalDate wareEndDate;
}
