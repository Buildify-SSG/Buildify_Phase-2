package com.buildify.wms.mapperTests;

import com.wareflow.buildify.domain.user.inventory.mapper.InventoryUserMapper;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2


public class InventoryTests {

    @Autowired(required = false)
    InventoryUserMapper inventoryUserMapper;

    @Test
    public void testInventoryList(){
        InventoryFilterDTO filter = InventoryFilterDTO.builder()
                .clientId("C003")
                .prodName("CPU")
                .categoryLevel1("컴퓨터")
                .categoryLevel2("그래픽카드")
                .categoryLevel3("NVIDIA")
                .sortBy("asc")
                .pageNum(1)
                .amount(10)
                .build();

        List<InventoryDTO> list = inventoryUserMapper.getFilteredInventory(filter);

        for (InventoryDTO dto : list) {
            log.info(dto.toString());
        }


    }
}
