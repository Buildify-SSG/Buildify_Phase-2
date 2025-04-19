package com.wareflow.buildify.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserWareHouseVO {
    private String wareId;
    private String clientID;
    private String warehousePosX;
    private int warehousePosY;
    private BigDecimal warehouseUsage;
    private BigDecimal contractArea;
    private Date wareStartDate;
    private Date wareEndDate;
}
