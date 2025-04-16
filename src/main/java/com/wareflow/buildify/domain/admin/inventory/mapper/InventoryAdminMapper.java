package com.wareflow.buildify.domain.admin.inventory.mapper;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;

import java.util.List;

public interface InventoryAdminMapper {

    public List<InventoryDTO> getFilteredInventory(InventoryFilterDTO filter);
    public int updateQuantity(String prodId, String wareId, String clientId, int quantity );
    public int deleteInventory(String prodId, String wareId, String clientId );
}
