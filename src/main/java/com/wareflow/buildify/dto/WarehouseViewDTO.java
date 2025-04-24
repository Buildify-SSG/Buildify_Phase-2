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
public class WarehouseViewDTO {
    private String wareId;
    private String wareCoord;
    private String wareAddress;
    private String adminNumber;
    private String clientId;
    private String clientName;
    private String clientBusinessNumber;
    private Date startDate;
    private Date endDate;
    private Date lastInboundDate;
    private Date lastOutboundDate;
}
