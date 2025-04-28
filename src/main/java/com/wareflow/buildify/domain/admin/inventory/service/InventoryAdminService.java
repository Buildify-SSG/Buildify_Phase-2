package com.wareflow.buildify.domain.admin.inventory.service;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryAdminDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;

import java.util.List;

/**
 * 관리자 재고 관리 서비스 인터페이스.
 */
public interface InventoryAdminService {
    /** 모든 재고 조회 */
    public List<InventoryAdminDTO>getAdminInventory();

    /** 필터 조건에 따른 재고 검색 */
    public List<InventoryAdminDTO>searchAdminInventory(InventoryFilterDTO filter);

    /** 대분류에 속한 중분류 목록 조회 */
    List<String> findMidCategoriesByLevel1(String category1);

    /** 중분류에 속한 소분류 목록 조회 */
    List<String> findSmallCategoriesByLevel2(String category2);

    /** 재고 수량 업데이트 (성공 시 true) */
    boolean updateQuantity(String inventoryId, int quantity);

    /** 여러 재고 삭제, 삭제된 건수 반환 */
    int deleteInventory(List<String> inventoryIds);







}
