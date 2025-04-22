package com.wareflow.buildify.domain.user.warehouse.service;

import com.wareflow.buildify.dto.UserWareHouseDTO;
import com.wareflow.buildify.vo.UserWareHouseVO;

import java.util.List;

public interface UserWarehouseService {

    boolean registerWarehouse(UserWareHouseDTO userWareHouseDTO);
    List<UserWareHouseDTO> getMyWarehouse(String clientId);
}
