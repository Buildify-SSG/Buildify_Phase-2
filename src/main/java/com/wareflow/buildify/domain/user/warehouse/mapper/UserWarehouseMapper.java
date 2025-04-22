package com.wareflow.buildify.domain.user.warehouse.mapper;

import com.wareflow.buildify.dto.UserWareHouseDTO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserWarehouseMapper {
    int insertWarehouse(UserWareHouseVO userWareHouseVO);
    List<UserWareHouseDTO> selectMyWarehouse(String clientId);
}
