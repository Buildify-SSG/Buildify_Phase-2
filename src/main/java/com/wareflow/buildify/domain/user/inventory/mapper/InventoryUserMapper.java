package com.wareflow.buildify.domain.user.inventory.mapper;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InventoryUserMapper {
    /**
     * 회원 재고 통합 필터 조회
     * 필터: 카테고리, 상품명, 창고, 정렬, 페이징 등
     */
    List<InventoryDTO> getFilteredInventory(InventoryFilterDTO filter);



}

