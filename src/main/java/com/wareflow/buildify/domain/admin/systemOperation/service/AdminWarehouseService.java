package com.wareflow.buildify.domain.admin.systemOperation.service;

import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;

import java.util.List;
import java.util.Map;

// 관리자 - 창고 레이아웃 조회 서비스 인터페이스
public interface AdminWarehouseService {

    //창고 레이아웃 정보 가져오기
    Map<String, Map<String,List<WarehouseViewDTO>>> getWarehouseList();

    // 창고 정보 가져오기
    List<WareHouseDTO> getWarehouseInfo();

}
