package com.wareflow.buildify.domain.admin.inventory.service;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;

import java.util.List;

public interface InventoryAdminService {
    public List<InventoryDTO> getAllInventory(InventoryFilterDTO filter);

    // 수량 업데이트 메소드, 성공시 true 반환
    public boolean updateQuantity(String prodId, String wareId, String clientId, int quantity);

    // 삭제 메소드
    public boolean deleteInventory(String prodId, String wareId, String clientId);


}
