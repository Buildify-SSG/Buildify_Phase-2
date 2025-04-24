package com.wareflow.buildify.dto;

import com.github.ckpoint.toexcel.annotation.ExcelHeader;
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
    @ExcelHeader(headerName = "고객 ID", priority = 0)
    private String clientId;
    @ExcelHeader(headerName = "고객 이름", priority = 1)
    private String clientName;
    private String clientBusinessNumber;
    @ExcelHeader(headerName = "계약 시작일", priority = 2)
    private LocalDate startDate;
    @ExcelHeader(headerName = "계약 종료일", priority = 3)
    private LocalDate endDate;
    @ExcelHeader(headerName = "남은 계약 일수", priority = 4)
    private int remainDays;
    @ExcelHeader(headerName = "계약 창고", priority = 5)
    private String wareId;
    @ExcelHeader(headerName = "계약 위치", priority = 6)
    private String wareCoord; // x*y 좌표 ex)A1,A2,....
    private String warePosX;
    private int warePosY;
    private int wareFee;  // 월 임대료

}
