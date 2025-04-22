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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Test
    public void testDeleteInventories() {
        // 1) 삭제 전: 전체 리스트 조회
        List<InventoryDTO> before = inventoryAdminService.getAdminInventory();
        assertThat(before)
                .as("테스트를 위해 재고가 최소 2개 이상 있어야 합니다")
                .hasSizeGreaterThanOrEqualTo(2);

        // 2) 지울 ID 두 개 선택
        String id1 = before.get(0).getInventoryId();
        String id2 = before.get(1).getInventoryId();
        log.info("▶ 삭제 대상 IDs: {}, {}", id1, id2);

        // 3) 서비스 호출
        int deletedCount = inventoryAdminService.deleteInventory(Arrays.asList(id1, id2));
        assertThat(deletedCount)
                .as("삭제된 건수는 요청한 ID 수(%d)와 같아야 합니다", 2)
                .isEqualTo(2);

        // 4) 삭제 후: 동일 ID들이 목록에서 사라졌는지 확인
        List<InventoryDTO> after = inventoryAdminService.getAdminInventory();
        List<String> remainingIds = after.stream()
                .map(InventoryDTO::getInventoryId)
                .collect(Collectors.toList());
        assertThat(remainingIds)
                .as("삭제된 ID들은 이제 목록에 없어야 합니다")
                .doesNotContain(id1, id2);

        // 5) 이미 삭제된 ID를 한 번 더 삭제 요청하면 0 리턴
        int deletedAgain = inventoryAdminService.deleteInventory(Arrays.asList(id1, id2));
        assertThat(deletedAgain)
                .as("이미 삭제된 항목을 재요청하면 0 반환")
                .isEqualTo(0);
    }

}
