package com.wareflow.buildify.domain.user.inventory.mapper;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.vo.InventoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface InventoryUserMapper {
    /**
     * 회원 재고 전체 조회
     *
     */
    List<InventoryDTO> getUserInventory(@Param("clientId") String clientId);

    public List<InventoryDTO> searchUserInventory(InventoryFilterDTO filter);

    List<String> findMidCategoriesByLevel1(  @Param("clientId") String clientId,
                                             @Param("category1") String category1);

    List<String> findSmallCategoriesByLevel2(@Param("clientId") String clientId,
                                             @Param("category2") String category2);



}

