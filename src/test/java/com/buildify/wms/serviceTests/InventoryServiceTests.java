package com.buildify.wms.serviceTests;


import com.wareflow.buildify.domain.admin.inventory.service.InventoryAdminService;
import com.wareflow.buildify.domain.user.inventory.service.InventoryUserService;
import com.wareflow.buildify.dto.InventoryDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class InventoryServiceTests {
    @Autowired(required = false)
    InventoryUserService inventoryUserService;

    @Autowired(required = false)
    InventoryAdminService inventoryAdminService;

    @Test
    public void testInventoryUserService() {
        inventoryUserService.getUserInventory();
    }

    @Test
    public void testInventoryAdminService(){
        inventoryAdminService.getAdminInventory();

    }

    @Test
    public void testInventoryUpdate(){
        String inventoryId = "INV001";

        // 1) 초기 수량 조회
        List<InventoryDTO> beforeList = inventoryAdminService.getAdminInventory();
        InventoryDTO beforeDto = beforeList.stream()
                .filter(i -> inventoryId.equals(i.getInventoryId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("INV001이 없어야 합니다."));
        int beforeQty = beforeDto.getQuantity();

        // 2) 서비스 호출 (수량 +5)
        boolean result = inventoryAdminService.updateQuantity(inventoryId, beforeQty + 5);
        assertThat(result)
                .as("updateQuantity는 true를 반환해야 합니다")
                .isTrue();

        // 3) 변경 후 다시 조회
        List<InventoryDTO> afterList = inventoryAdminService.getAdminInventory();
        InventoryDTO afterDto = afterList.stream()
                .filter(i -> inventoryId.equals(i.getInventoryId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("INV001이 없어야 합니다."));
        int afterQty = afterDto.getQuantity();

        assertThat(afterQty)
                .as("수량이 %d에서 %d로 변경돼야 합니다", beforeQty, beforeQty + 5)
                .isEqualTo(beforeQty + 5);

    }

}
