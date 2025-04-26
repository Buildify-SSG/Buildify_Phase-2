package com.wareflow.buildify.domain.user.inventory.service;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;


import java.util.List;

public interface InventoryUserService {
    List<InventoryDTO> getUserInventory();
    List<InventoryDTO>searchUserInventory(InventoryFilterDTO filter);

    List<String> findMidCategoriesByLevel1(String clientId,String category1);

    List<String> findSmallCategoriesByLevel2(String clientId,String category2);


}
