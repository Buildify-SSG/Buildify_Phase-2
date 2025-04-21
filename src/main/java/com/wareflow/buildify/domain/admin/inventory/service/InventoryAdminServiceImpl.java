package com.wareflow.buildify.domain.admin.inventory.service;

import com.wareflow.buildify.domain.admin.inventory.mapper.InventoryAdminMapper;
import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class InventoryAdminServiceImpl implements InventoryAdminService {

    private final InventoryAdminMapper inventoryAdminMapper;

    @Override
    public List<InventoryDTO> getAdminInventory() {
        log.info("▶ 호출 전 매퍼(빈): {}", inventoryAdminMapper);
        List<InventoryDTO> list = inventoryAdminMapper.getAdminInventory();
        log.info("▶ 매퍼가 꺼내온 리스트: {}", list.size());
        return list;
    }

    @Override
    public List<InventoryDTO> searchAdminInventory(InventoryFilterDTO filter) {
        return inventoryAdminMapper.searchAdminInventory(filter);
    }

    @Override
    public List<String> findMidCategoriesByLevel1(String category1) {
        System.out.println("📢 Service 들어옴, category1 = " + category1);

        List<String> result = inventoryAdminMapper.findMidCategoriesByLevel1(category1);

        System.out.println("📢 Service result = " + result);

        return result;

    }

    @Override
    public List<String> findSmallCategoriesByLevel2(String category2) {
        return inventoryAdminMapper.findSmallCategoriesByLevel2(category2);
    }

    //    @Override
//    public List<String> findMidCategoriesByLevel1(String category1) {
//        return inventoryAdminMapper.findMidCategoriesByLevel1(category1);
//    }
//
//    @Override
//    public List<String> findSmallCategoriesByLevel2(String category2) {
//        return inventoryAdminMapper.findSmallCategoriesByLevel2(category2);
//    }


    @Override
    public boolean updateQuantity(String inventoryId, int quantity) {
        return false;
    }

    @Override
    public boolean deleteInventory(String inventoryId) {
        return false;
    }
}
