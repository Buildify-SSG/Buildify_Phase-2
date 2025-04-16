package com.wareflow.buildify.domain.user.inventory.service;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;

import java.util.List;

public interface InventoryUserService {
    List<InventoryDTO> getFilteredInventory(InventoryFilterDTO filter);


}
