package com.wareflow.buildify.domain.admin.inventory.mapper;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryAdminDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 관리자 재고 관리 기능을 위한 MyBatis 매퍼 인터페이스입니다.
 * 재고 조회, 검색, 수정, 삭제 메소드를 정의합니다.
 */
public interface InventoryAdminMapper {


    /**
     * 모든 재고 정보를 조회합니다.
     *@return 재고 정보를 담은 InventoryAdminDTO 리스트
     */
    public List<InventoryAdminDTO>getAdminInventory();

    /**
     * 필터 조건에 따라 재고 정보를 검색합니다.
     * @param filter 검색 및 필터 조건을 담은 DTO
     * @return 필터된 재고 정보를 담은 InventoryAdminDTO 리스트
     */
    public List<InventoryAdminDTO>searchAdminInventory(InventoryFilterDTO filter);

    /**
     * 특정 대분류(category1)에 속한 중분류 목록을 조회합니다.
     * @param category1 대분류 이름
     * @return 중분류 이름 목록
     */
    List<String> findMidCategoriesByLevel1(String category1);

    /**
     * 특정 중분류(category2)에 속한 소분류 목록을 조회합니다.
     * @param category2 중분류 이름
     * @return 소분류 이름 목록
     */
    List<String> findSmallCategoriesByLevel2(String category2);

    /**
     * 주어진 재고 ID의 수량을 업데이트합니다.
     * @param inventoryId 수정할 재고의 고유 식별자
     * @param quantity    설정할 새로운 수량 (0 이상)
     * @return 업데이트된 행 수
     */
    int updateQuantity(@Param("inventoryId") String inventoryId, @Param("quantity") int quantity);

    /**
     * 주어진 재고 ID의 재고 정보를 삭제합니다.
     * @param inventoryId 삭제할 재고의 고유 식별자
     * @return 삭제된 행 수
     */
    int deleteInventory(@Param("inventoryId") String inventoryId);



}
