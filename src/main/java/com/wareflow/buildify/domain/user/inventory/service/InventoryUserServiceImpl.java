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

@Service
@RequiredArgsConstructor
@Log4j2
public class InventoryUserServiceImpl implements InventoryUserService {

    private final InventoryUserMapper inventoryUserMapper;  // final로 선언

    @Override
    public List<InventoryDTO> getUserInventory() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        log.info("서비스 진입 성공");

        String clientId = userDetails.getClientId();

        log.info("서비스 회원 ID : {}",clientId);

        log.info("재고 회원 서비스");


        List<InventoryDTO> list = inventoryUserMapper.getUserInventory(clientId);

            log.info("서비스 리스트 사이즈 : {}", list.size());
//        }

        return list;

    }

    @Override
    public List<InventoryDTO> searchUserInventory(InventoryFilterDTO filter) {
        return inventoryUserMapper.searchUserInventory(filter);
    }

    @Override
    public List<String> findMidCategoriesByLevel1(String category1) {
        System.out.println("📢 Service 들어옴, category1 = " + category1);

        List<String> result = inventoryUserMapper.findMidCategoriesByLevel1(category1);

        System.out.println("📢 Service result = " + result);

        return result;
    }

    @Override
    public List<String> findSmallCategoriesByLevel2(String category2) {
        return inventoryUserMapper.findSmallCategoriesByLevel2(category2);
    }
}
