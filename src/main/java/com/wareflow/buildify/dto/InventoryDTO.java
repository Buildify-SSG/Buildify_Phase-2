package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class InventoryDTO {
    private String prodId;
    private String prodName;
    private String brand;
    private String clientId;

    private String wareId;
    private String wareName;
    private int quantity;

    private Date lastInboundDate;
    private Date lastOutboundDate;

    private String categoryLarge;
    private String categoryMedium;
    private String categorySmall;

    private String sortBy; // asc / desc
}
