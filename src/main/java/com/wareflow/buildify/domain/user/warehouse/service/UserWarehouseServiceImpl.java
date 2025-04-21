package com.wareflow.buildify.domain.user.warehouse.service;

import com.wareflow.buildify.domain.user.warehouse.mapper.UserWarehouseMapper;
import com.wareflow.buildify.dto.UserWareHouseDTO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserWarehouseServiceImpl implements UserWarehouseService {

    private final UserWarehouseMapper userWarehouseMapper;

    @Override
    public boolean registerWarehouse(UserWareHouseDTO userWareHouseDTO) {
        ModelMapper modelMapper = new ModelMapper();
        UserWareHouseVO vo =  modelMapper.map(userWareHouseDTO, UserWareHouseVO.class);
       return userWarehouseMapper.insertWarehouse(vo) == 1;
    }
}
