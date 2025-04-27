package com.wareflow.buildify.domain.admin.outbound.controller;

import com.wareflow.buildify.domain.admin.outbound.service.AdminOutboundService;
import com.wareflow.buildify.dto.AdminOutboundRequestDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.OutboundInventoryDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
@Log4j2
@RequiredArgsConstructor
public class AdminOutboundController {
    private final AdminOutboundService adminOutboundService;

    @GetMapping("/admin/pages/outbound/outbound-1")
    public String outboundChecklist(@RequestParam(defaultValue = "1") int page, Model model) {
        List<OutboundInventoryDTO> dtoList = adminOutboundService.outboundCheckList();
        log.info(dtoList);
        log.info("아웃바운드인서트 컨트롤러");
        log.info(":흰색_확인_표시: body: {}", model.getAttribute("body"));

        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/admin/pages/outbound/outbound-1.jsp");

        return "admin/layouts/adminlayout";

    }

    @GetMapping("/admin/pages/outbound/outbound-2")
    public String outboundList(@RequestParam(defaultValue = "1") int page, Model model) {
        log.info("아웃바운드리스트컨트롤러");
        List<OutboundDTO> dtoList = adminOutboundService.adminOutboundList();
        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/admin/pages/outbound/outbound-2.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/admin/pages/outbound/outbound-1/modal-info")
    @ResponseBody
    public List<OutboundInventoryDTO> adminOutboundCheckInfo(@RequestParam List<String> outboundIds) {
        log.info("🔍 모달용 창고ID 리스트: {}", outboundIds);
        return adminOutboundService.adminOutboundCheckInfo(outboundIds);// productDTO 리스트 반환

    }

    @PostMapping("/admin/pages/outbound/outbound-1/request")
    public ResponseEntity<String> adminOutnboundRequests(@RequestBody Map<String, Object> request) {
        log.info("🔥 requestoutbound() 진입");
        log.info("📦 받은 사이즈: {}", request.size());

        List<String> outboundIds = (List<String>) request.get("outboundIds");
//        List<Integer> quantities = (List<Integer>) request.get("quantities");
//        List<String > prodIds = (List<String>) request.get("prodIds");
//        log.info(quantities);
        List<AdminOutboundRequestDTO> requestList = new ArrayList<>();
        log.info("어드민 아웃바운드 리퀘스트 22");
        for (int i = 0; i < outboundIds.size(); i++) {
            AdminOutboundRequestDTO dto = new AdminOutboundRequestDTO();
            dto.setOutboundId(outboundIds.get(i));
//            dto.setQuantity(quantities.get(i));
//            dto.setProdId(prodIds.get(i));
            requestList.add(dto);
        }
        log.info("여기까지오는지확인이필용하비ㅓㅣㄴㄹㄴㄹ");
        log.info("이게맞나요{}",requestList);
        log.info("어드민 아웃바운드 리퀘스트 33");
        try {
            adminOutboundService.adminOutnboundRequests(requestList);
            return ResponseEntity.ok("출고 요청 성공");
        } catch (Exception e) {
            log.info("❌ 서버 처리 중 에러 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");
        }


    }
}
