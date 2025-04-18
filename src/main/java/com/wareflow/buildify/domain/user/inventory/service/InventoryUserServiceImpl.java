package com.wareflow.buildify.domain.user.inventory.service;

import com.wareflow.buildify.domain.user.inventory.mapper.InventoryUserMapper;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class InventoryUserServiceImpl implements InventoryUserService {

    private final InventoryUserMapper inventoryUserMapper;  // final로 선언

    @Override
    public List<InventoryDTO> getUserInventory() {
        log.info("▶ 호출 전 매퍼(빈): {}", inventoryUserMapper);
        List<InventoryDTO> list = inventoryUserMapper.getUserInventory();
        log.info("▶ 매퍼가 꺼내온 리스트: {}", list.size());
        return list;

    }
}
