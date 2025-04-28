package com.wareflow.buildify.domain.user.inventory.service;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.inventory.mapper.InventoryUserMapper;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.vo.InventoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor// 생성자 주입을 위한 Lombok 어노테이션
@Log4j2

/**
 * 사용자 재고 조회 및 필터링 기능을 구현한 서비스 클래스입니다.
 */
public class InventoryUserServiceImpl implements InventoryUserService {

    // MyBatis 매퍼 인스턴스를 주입받아 데이터베이스 연동에 사용합니다.
    private final InventoryUserMapper inventoryUserMapper;

    /**
     * 현재 로그인한 사용자의 재고 목록을 조회합니다.
     */
    @Override
    public List<InventoryDTO> getUserInventory() {
        // Spring Security 컨텍스트에서 인증 정보를 가져옵니다.
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        log.info("서비스 진입 성공");

        // 사용자 정보에서 clientId를 추출합니다.
        String clientId = userDetails.getClientId();

        log.info("서비스 회원 ID : {}",clientId);

        log.info("재고 회원 서비스");


        // 매퍼를 통해 해당 clientId의 재고 목록을 조회합니다.
        List<InventoryDTO> list = inventoryUserMapper.getUserInventory(clientId);

            log.info("서비스 리스트 사이즈 : {}", list.size());
//        }


        // 조회된 재고 리스트를 반환
        return list;

    }


    /**
     * 필터 조건에 따른 재고 검색 기능을 제공합니다.
     */
    @Override
    public List<InventoryDTO> searchUserInventory(InventoryFilterDTO filter) {
        // 매퍼에 filter DTO를 전달하여 검색 결과를 가져옵니다.
        return inventoryUserMapper.searchUserInventory(filter);
    }

    /**
     * AJAX 요청으로 전달된 대분류에 해당하는 중분류 리스트를 조회합니다.
     */
    @Override
    public List<String> findMidCategoriesByLevel1(String clientId,String category1) {
        System.out.println("📢 Service 들어옴, category1 = " + category1);

        return inventoryUserMapper.findMidCategoriesByLevel1(clientId,category1);
    }


    /**
     * AJAX 요청으로 전달된 중분류에 해당하는 소분류 리스트를 조회합니다.
     */
    @Override
    public List<String> findSmallCategoriesByLevel2(String clientId, String category2) {
        return inventoryUserMapper.findSmallCategoriesByLevel2(clientId,category2);
    }
}
