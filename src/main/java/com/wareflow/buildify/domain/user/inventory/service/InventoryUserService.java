package com.wareflow.buildify.domain.user.inventory.service;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;


import java.util.List;


/**
 * 회원 재고 관리 서비스 인터페이스.
 * 로그인한 회원의 재고 조회 및 필터링 기능을 제공합니다.
 */
public interface InventoryUserService {

    /**
     * 로그인한 회원의 전체 재고 목록을 조회합니다.
     * @return 회원 재고 목록 DTO 리스트
     */
    List<InventoryDTO> getUserInventory();


    /**
     * 필터 조건에 따라 회원의 재고를 검색합니다.
     * @param filter 검색 기준 및 필터 정보를 담은 DTO
     * @return 필터된 재고 목록 DTO 리스트
     */
    List<InventoryDTO>searchUserInventory(InventoryFilterDTO filter);


    /**
     * 특정 대분류에 속한 중분류 목록을 조회합니다.
     * @param clientId  로그인한 회원의 식별자
     * @param category1 대분류 이름
     * @return 중분류 이름 목록
     */
    List<String> findMidCategoriesByLevel1(String clientId,String category1);


    /**
     * 특정 중분류에 속한 소분류 목록을 조회합니다.
     * @param clientId  로그인한 회원의 식별자
     * @param category2 중분류 이름
     * @return 소분류 이름 목록
     */
    List<String> findSmallCategoriesByLevel2(String clientId,String category2);


}
