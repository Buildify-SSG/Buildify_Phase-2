package com.wareflow.buildify.domain.admin.systemOperation.controller;

import com.wareflow.buildify.dto.WareHouseDTO;
import org.springframework.ui.Model;

import java.util.List;

// 관리자 - 창고 레이아웃 조회 컨트롤러 구현체
public class AdminWarehouseControllerImpl implements AdminWarehouseController{

    // 창고 레이아웃 정보 가져오기
    @Override
    public List<WareHouseDTO> getWarehouseList(Model model) {
        return null;
    }
}
