package com.wareflow.buildify.domain.user.warehouse.service;

import com.wareflow.buildify.domain.user.warehouse.mapper.UserWarehouseMapper;
import com.wareflow.buildify.dto.UserWareHouseDTO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserWarehouseServiceImpl implements UserWarehouseService {

    private final UserWarehouseMapper userWarehouseMapper;

    @Override
    public boolean registerWarehouse(UserWareHouseDTO dto) {
        UserWareHouseVO vo = UserWareHouseVO.builder()
                .wareId(dto.getWareId())
                .clientId(dto.getClientId())
                .warehousePosX(dto.getWarehousePosX())
                .warehousePosY(dto.getWarehousePosY())
                .warehouseUsage(dto.getWarehouseUsage())
                .contractArea(dto.getContractArea())
                .wareStartDate(dto.getWareStartDate())
                .wareEndDate(dto.getWareEndDate())
                .build();

        log.info("register warehouse: {}", vo.getWareId());
       return userWarehouseMapper.insertWarehouse(vo) == 1;
    }
}
