package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.systemOperation.mapper.AdminWarehouseMapper;
import com.wareflow.buildify.domain.admin.systemOperation.service.AdminWarehouseService;
import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class WarehouseViewServiceTests {

    @Autowired
    AdminWarehouseService adminWarehouseService;

    @Autowired
    AdminWarehouseMapper adminWarehouseMapper;
    
    @Test
    @DisplayName("창고 조회 서비스 테스트")
    public void viewTest(){
        Map<String,Map<String, List<WarehouseViewDTO>>> layoutmap = adminWarehouseService.getWarehouseList();

        log.info(layoutmap.size());
        log.info(layoutmap.get("W001").get("A1"));
        log.info(layoutmap.get("W001").get("A2"));
        log.info(layoutmap.get("W001").get("A3"));
        log.info(layoutmap.get("W001").get("A4"));
        log.info(layoutmap.get("W001").get("A5"));

    }

    @Test
    @DisplayName("창고 정보 불러오기 테스트")
    public void wareInfo(){
        List<WareHouseDTO> wareHouseDTO = adminWarehouseService.getWarehouseInfo();
        log.info("List Size : " + wareHouseDTO.size());
        for (int i = 0; i < wareHouseDTO.size(); i++) {
            log.info(wareHouseDTO.get(i));
        }
    }
}
