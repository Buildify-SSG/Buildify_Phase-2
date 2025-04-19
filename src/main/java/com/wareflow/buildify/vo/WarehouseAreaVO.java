package com.wareflow.buildify.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseAreaVO {
    private String wareId;
    private String warehousePosX;
    private int warehousePosY;
    private BigDecimal areaSize;
    private int rentalFee;
}
