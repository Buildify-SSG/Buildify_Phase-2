package com.wareflow.buildify.domain.user.inventory.service;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;

import java.util.List;

public class InventoryUserServiceImpl implements InventoryUserService {

    @Override
    public List<InventoryDTO> getFilteredInventory(InventoryFilterDTO filter) {
        return List.of();
    }
}
