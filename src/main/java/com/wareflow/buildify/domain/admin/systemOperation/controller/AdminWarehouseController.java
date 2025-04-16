package com.wareflow.buildify.domain.admin.systemOperation.controller;

import com.wareflow.buildify.dto.WareHouseDTO;
import org.springframework.ui.Model;

import java.util.List;

// 관리자 - 창고 레이아웃 조회 컨트롤러 인터페이스
public interface AdminWarehouseController {

    // 창고 레이아웃 정보 가져오기
    List<WareHouseDTO> getWarehouseList(Model model);
}
