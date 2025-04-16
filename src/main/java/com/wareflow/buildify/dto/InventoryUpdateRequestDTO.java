package com.wareflow.buildify.dto;

import lombok.Data;

@Data

public class InventoryUpdateRequestDTO {
    private String prodId;
    private String wareId;
    private String clientId;
    private int quantity;

}
