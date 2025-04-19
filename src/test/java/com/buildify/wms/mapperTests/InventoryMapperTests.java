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
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2


public class InventoryMapperTests {

    @Autowired(required = false)
    InventoryUserMapper inventoryUserMapper;

    @Test
    public void testInventoryList() {

        List<InventoryDTO> list = inventoryUserMapper.getUserInventory();

       log.info(list.size());
       log.info(list);


        assertThat(list).isNotEmpty();

        InventoryDTO first = list.get(0);
        assertThat(first.getClientId()).isEqualTo("CLT-001-AAA");
        assertThat(first.getProdName()).isNotEmpty(); // or .contains("ROG")
    }

    @Test
    public void testInventorySearch(){

        InventoryFilterDTO filter = new InventoryFilterDTO();
        filter.setCategory1("컴퓨터");
        filter.setSearchType("prodName");
        filter.setKeyword("i7-12700K");

        List<InventoryDTO> result = inventoryUserMapper.searchUserInventory(filter);

        assertThat(result).isNotNull();
        log.info("전체 조회 결과 수: {}" , result.size());
        for (InventoryDTO dto : result) {
            log.info("▶ 전체 조회 상품: {}", dto);
        }



    }


    }

