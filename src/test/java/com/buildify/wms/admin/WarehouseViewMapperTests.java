package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.systemOperation.mapper.AdminWarehouseMapper;
import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class WarehouseViewMapperTests {

    @Autowired
    AdminWarehouseMapper adminWarehouseMapper;

    @Test
    @Transactional
    @DisplayName("창고 레이아웃 테스트")
    public void viewTest(){
        log.info("창고 정보 불러오기 테스트 시작");
        List<WarehouseViewDTO> viewDTOS = adminWarehouseMapper.getWarehouseList();
        log.info(viewDTOS.size());
        for (WarehouseViewDTO warehouseViewDTO : viewDTOS){
            log.info("창고 : {}{} 창고주소 : {} 관리자 : {} 고객 : {}-{} 사업자번호 : {} 계약일 {}-{} 입고 {} 출고 {}",
                    warehouseViewDTO.getWareId(),warehouseViewDTO.getWareCoord(),
                    warehouseViewDTO.getWareAddress(),warehouseViewDTO.getAdminNumber(),
                    warehouseViewDTO.getClientId(),warehouseViewDTO.getClientName(),
                    warehouseViewDTO.getClientBusinessNumber(),
                    warehouseViewDTO.getStartDate(),warehouseViewDTO.getEndDate(),
                    warehouseViewDTO.getLastInboundDate(),warehouseViewDTO.getLastOutboundDate());
        }
    }

    @Test
    @Transactional
    @DisplayName("창고 정보 불러오기 테스트")
    public void wareInfo(){
        List<WareHouseDTO> wareHouseDTO = adminWarehouseMapper.getWarehouseInfo();
        log.info("List Size : " + wareHouseDTO.size());
        for (int i = 0; i < wareHouseDTO.size(); i++) {
            log.info(wareHouseDTO.get(i));
        }
    }
}
