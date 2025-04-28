package com.wareflow.buildify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

/**
 * 재고 수량 업데이트를 위한 DTO 클래스
 * 사용자가 수정할 재고 ID와 새로운 수량 정보를 담는다.
 */
public class InventoryUpdateDTO {
    @NotBlank
    String inventoryId;
    @Min(0)   int quantity;
}
