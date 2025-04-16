package com.wareflow.buildify.domain.admin.inventory.service;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;

import java.util.List;

public class InventoryAdminServiceImpl implements InventoryAdminService {
    @Override
    public List<InventoryDTO> getAllInventory(InventoryFilterDTO filter) {
        return List.of();
    }

    @Override
    public boolean updateQuantity(String prodId, String wareId, String clientId, int quantity) {
        return false;
    }

    @Override
    public boolean deleteInventory(String prodId, String wareId, String clientId) {
        return false;
    }
}
