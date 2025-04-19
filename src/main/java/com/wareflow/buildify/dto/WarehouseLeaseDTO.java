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
public class WarehouseLeaseDTO {
    private String clientID;
    private String clientName;
    private Date startDate;
    private Date endDate;
    private String wareId;
    private String wareCoord; // x*y 좌표 ex)A1,A2,....
    private int wareFee;  // 월 임대료
}
