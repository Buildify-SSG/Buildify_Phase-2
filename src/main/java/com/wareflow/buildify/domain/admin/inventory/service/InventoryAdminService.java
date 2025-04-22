package com.wareflow.buildify.domain.admin.inventory.service;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;

import java.util.List;

public interface InventoryAdminService {
    public List<InventoryDTO>getAdminInventory();

    public List<InventoryDTO>searchAdminInventory(InventoryFilterDTO filter);

    List<String> findMidCategoriesByLevel1(String category1);

    List<String> findSmallCategoriesByLevel2(String category2);

    boolean updateQuantity(String inventoryId, int quantity);

    int deleteInventory(List<String> inventoryIds);







}
