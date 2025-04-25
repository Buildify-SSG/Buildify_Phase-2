package com.wareflow.buildify.domain.admin.inventory.mapper;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryAdminDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryAdminMapper {

    public List<InventoryAdminDTO>getAdminInventory();

    public List<InventoryAdminDTO>searchAdminInventory(InventoryFilterDTO filter);

    List<String> findMidCategoriesByLevel1(String category1);

    List<String> findSmallCategoriesByLevel2(String category2);

    int updateQuantity(@Param("inventoryId") String inventoryId, @Param("quantity") int quantity);

    int deleteInventory(@Param("inventoryId") String inventoryId);



}
