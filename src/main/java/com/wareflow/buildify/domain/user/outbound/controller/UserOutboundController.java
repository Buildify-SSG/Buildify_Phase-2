package com.wareflow.buildify.domain.user.outbound.controller;

import com.wareflow.buildify.domain.user.outbound.service.UserOutboundService;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.OutboundInventoryDTO;
import com.wareflow.buildify.dto.ProductDTO;
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
public class UserOutboundController {
    private final UserOutboundService userOutboundService;

    @GetMapping("/users/pages/outbound/outbound-1")
    public String outboundInsertList(@RequestParam(defaultValue = "1") int page, Model model) {
        List<OutboundInventoryDTO> dtoList = userOutboundService.outboundInsertList();
        log.info("아웃바운드인서트 컨트롤러");
        log.info(":흰색_확인_표시: body: {}", model.getAttribute("body"));

        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/users/pages/outbound/outbound-1.jsp");

        return "users/layouts/userlayout";

    }

    @GetMapping("/users/pages/outbound/outbound-2")
    public String outboundList(@RequestParam(defaultValue = "1") int page, Model model){
        log.info("아웃바운드리스트컨트롤러");
        List<OutboundDTO> dtoList = userOutboundService.outboundList();
        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/users/pages/outbound/outbound-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/users/pages/outbound/outbound-1/modal-info")
    @ResponseBody
    public List<OutboundInventoryDTO> outboundInsertInfo(@RequestParam List<String> inventoryIds) {
        log.info("🔍 모달용 창고ID 리스트: {}", inventoryIds);
        return userOutboundService.outboundInsertInfo(inventoryIds);  // productDTO 리스트 반환
    }

    @PostMapping("/users/pages/outbound/outbound-1/request")
    public ResponseEntity<String> requestoutbound(@RequestBody Map<String, Object> request) {
        log.info("🔥 requestoutbound() 진입");
        log.info("📦 받은 사이즈: {}", request.size());

        List<String > inventoryIds = (List<String>) request.get("inventoryIds");
        List<Integer> quantities = (List<Integer>) request.get("quantities");

        List<InventoryDTO> requestList = new ArrayList<>();

        for (int i = 0; i < inventoryIds.size(); i++) {
            InventoryDTO dto = new InventoryDTO();
            dto.setInventoryId(inventoryIds.get(i));
            dto.setQuantity(quantities.get(i));
            requestList.add(dto);
        }
        log.info("여기까지오는지확인이필용하비ㅓㅣㄴㄹㄴㄹ");
        log.info(requestList );

        try {
            userOutboundService.insertOutnboundRequests(requestList);
            return ResponseEntity.ok("출고 요청 성공");
        } catch (Exception e){
            log.info("❌ 서버 처리 중 에러 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");
        }
    }

//    @PostMapping("/users/pages/outbound/outbound-1/request")
//    public ResponseEntity<String> requestoutbound(@RequestBody Map<String, Object> request) {
//        log.info("🔥 requestoutbound() 진입");
//        log.info("📦 받은 사이즈: {}", request.size());
//
//        List<String > inventoryIds = (List<String>) request.get("inventoryIds");
//        List<Integer> quantities = (List<Integer>) request.get("quantities");
//
//        List<InventoryDTO> requestList = new ArrayList<>();
//
//        for (int i = 0; i < inventoryIds.size(); i++) {
//            InventoryDTO dto = new InventoryDTO();
//            dto.setInventoryId(inventoryIds.get(i));
//            dto.setQuantity(quantities.get(i));
//            requestList.add(dto);
//        }
//        log.info("여기까지오는지확인이필용하비ㅓㅣㄴㄹㄴㄹ");
//        log.info(requestList );
//
//        try {
//            userOutboundService.insertOutnboundRequests(requestList);
//            return ResponseEntity.ok("출고 요청 성공");
//        } catch (Exception e){
//            log.info("❌ 서버 처리 중 에러 발생", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버에러");
//        }
//    }

}
