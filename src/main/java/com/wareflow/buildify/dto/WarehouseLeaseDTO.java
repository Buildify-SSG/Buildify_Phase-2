package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseLeaseDTO {
    private String clientId;
    private String clientName;
    private String clientBusinessNumber;
    private LocalDate startDate;
    private LocalDate endDate;
    private int remainDays;
    private String wareId;
    private String wareCoord; // x*y 좌표 ex)A1,A2,....
    private String warePosX;
    private int warePosY;
    private int wareFee;  // 월 임대료

}
