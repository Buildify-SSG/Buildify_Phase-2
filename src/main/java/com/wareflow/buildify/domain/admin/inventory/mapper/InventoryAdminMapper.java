package com.wareflow.buildify.domain.admin.inventory.mapper;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InventoryAdminMapper {

    public List<InventoryDTO>getAdminInventory();

    public List<InventoryDTO>searchAdminInventory(InventoryFilterDTO filter);

    List<String> findMidCategoriesByLevel1(String category1);

    List<String> findSmallCategoriesByLevel2(String category2);



}
