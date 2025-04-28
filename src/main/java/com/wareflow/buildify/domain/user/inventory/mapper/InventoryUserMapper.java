package com.wareflow.buildify.domain.user.inventory.mapper;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.vo.InventoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper

/**
 * 회원용 재고 관리 기능을 위한 MyBatis 매퍼 인터페이스입니다.
 * 로그인한 회원의 재고 조회, 검색, 및 카테고리 분류 목록을 제공합니다.
 */
public interface InventoryUserMapper {

    /**
     * 해당 회원의 전체 재고 목록을 조회합니다.
     * @param clientId 로그인한 회원의 고유 식별자
     * @return 회원이 보유한 InventoryDTO 리스트
     */
    List<InventoryDTO> getUserInventory(@Param("clientId") String clientId);

    /**
     * 필터 조건에 따라 해당 회원의 재고를 검색합니다.
     * @param filter 검색 및 필터링 기준을 담은 DTO
     * @return 필터된 InventoryDTO 리스트
     */
    public List<InventoryDTO> searchUserInventory(InventoryFilterDTO filter);


    /**
     * 해당 회원과 대분류(category1)에 속한 중분류 목록을 조회합니다.
     * @param clientId  로그인한 회원의 고유 식별자
     * @param category1 대분류 이름
     * @return 중분류 이름 목록
     */
    List<String> findMidCategoriesByLevel1(  @Param("clientId") String clientId,
                                             @Param("category1") String category1);


    /**
     * 해당 회원과 중분류(category2)에 속한 소분류 목록을 조회합니다.
     * @param clientId  로그인한 회원의 고유 식별자
     * @param category2 중분류 이름
     * @return 소분류 이름 목록
     */
    List<String> findSmallCategoriesByLevel2(@Param("clientId") String clientId,
                                             @Param("category2") String category2);



}

