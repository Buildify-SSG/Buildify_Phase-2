package com.wareflow.buildify.domain.admin.systemOperation.mapper;

import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;
import com.wareflow.buildify.vo.UserVO;
import com.wareflow.buildify.vo.WareHouseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 관리자 - 창고 레이아웃 조회 Mapper
@Mapper
public interface AdminWarehouseMapper {

    //창고 레이아웃 정보 가져오기
    List<WarehouseViewDTO> getWarehouseList();

    List<WareHouseDTO> getWarehouseInfo();


}
