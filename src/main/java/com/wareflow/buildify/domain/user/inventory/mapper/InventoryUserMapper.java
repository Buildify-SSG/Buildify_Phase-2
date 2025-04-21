package com.wareflow.buildify.domain.user.inventory.mapper;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryUserMapper {
    /**
     * 회원 재고 전체 조회
     *
     */
    public List<InventoryDTO> getUserInventory();

    public List<InventoryDTO> searchUserInventory(InventoryFilterDTO filter);

    List<String> findMidCategoriesByLevel1(String category1);

    List<String> findSmallCategoriesByLevel2(String category2);



}

