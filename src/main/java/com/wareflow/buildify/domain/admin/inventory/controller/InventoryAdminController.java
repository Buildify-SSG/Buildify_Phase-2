package com.wareflow.buildify.domain.admin.inventory.controller;

import com.wareflow.buildify.dto.InventoryDeleteRequestDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import com.wareflow.buildify.dto.InventoryUpdateRequestDTO;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface InventoryAdminController {
    /**
     * 관리자 재고 목록 조회 (카테고리, 상품명, 창고 등 필터 지원)
     */
    String getInventory(InventoryFilterDTO filter, Model model);

    /**
     * 관리자 재고 수량 수정 요청 처리 후 목록 페이지로 redirect
     */
    String updateInventory(InventoryUpdateRequestDTO request, RedirectAttributes rttr);

    /**
     * 관리자 재고 삭제 요청 처리 후 목록 페이지로 redirect
     */
    String deleteInventory(InventoryDeleteRequestDTO request, RedirectAttributes rttr);
}
