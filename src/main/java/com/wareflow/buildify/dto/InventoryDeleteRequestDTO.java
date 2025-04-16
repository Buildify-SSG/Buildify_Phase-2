package com.wareflow.buildify.dto;

import lombok.Data;

@Data
public class InventoryDeleteRequestDTO {
    private String prodId;
    private String wareId;
    private String clientId;
}
