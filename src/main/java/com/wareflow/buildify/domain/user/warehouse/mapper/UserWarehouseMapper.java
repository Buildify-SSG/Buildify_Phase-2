package com.wareflow.buildify.domain.user.warehouse.mapper;

import com.wareflow.buildify.vo.UserWareHouseVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserWarehouseMapper {
    int insertWarehouse(UserWareHouseVO userWareHouseVO);
}
