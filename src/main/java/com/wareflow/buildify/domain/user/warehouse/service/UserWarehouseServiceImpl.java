package com.wareflow.buildify.domain.user.warehouse.service;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
                .wareStartDate(dto.getWareStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .wareEndDate(dto.getWareEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .build();

        log.info("register warehouse: {}", vo.getWareId());
       return userWarehouseMapper.insertWarehouse(vo) == 1;
    }

    @Override
    public List<UserWareHouseDTO> getMyWarehouse(String clientId) {
        List<UserWareHouseDTO> list = userWarehouseMapper.selectMyWarehouse(clientId);

        list.sort(Comparator.comparing(UserWareHouseDTO::getWarehousePosX).thenComparing(UserWareHouseDTO::getWarehousePosY));
//        list.sort(Comparator.comparing(UserWareHouseDTO::getWareEndDate));
        return list;
    }

}
