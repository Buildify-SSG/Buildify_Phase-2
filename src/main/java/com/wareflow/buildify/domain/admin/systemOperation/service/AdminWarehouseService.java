package com.wareflow.buildify.domain.admin.systemOperation.service;

import com.wareflow.buildify.dto.WareHouseDTO;

import java.util.List;

// 관리자 - 창고 레이아웃 조회 서비스 인터페이스
public interface AdminWarehouseService {

    //창고 레이아웃 정보 가져오기
    List<WareHouseDTO> getWarehouseList();

}
