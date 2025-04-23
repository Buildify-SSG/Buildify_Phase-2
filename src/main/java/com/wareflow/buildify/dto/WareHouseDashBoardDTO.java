package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WareHouseDashBoardDTO {

    private String wareId;      // 창고 ID
    private double contractRate; // 계약률 (ex. 0.52)
    private double usageRate;    // 가용률 (ex. 0.1284)

}
