package com.wareflow.buildify.domain.admin.inbound.controller;

import com.wareflow.buildify.domain.admin.inbound.service.AdminInboundService;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
@Log4j2
@RequiredArgsConstructor
public class AdminInboundController {
    private final AdminInboundService adminInboundService;

    @GetMapping("/admin/pages/inbound/inbound-2")
    public String Admininboundtlist(@RequestParam(defaultValue = "1") int page, Model model) {

        log.info("inboundinsertlist........");
        List<InboundProduntDTO> dtoList = adminInboundService.AdminInboundList();
        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/admin/pages/inbound/inbound-2.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/admin/pages/inbound/inbound-1")
    public String Admininboundchecklist(@RequestParam(defaultValue = "1") int page, Model model) {

        log.info("inboundinsertlist........");
        List<InboundProduntDTO> dtoList = adminInboundService.AdminInboundCheckList();
        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/admin/pages/inbound/inbound-1.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/admin/pages/inbound/inbound-2/search")
    public String searchInboundList(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam("searchType") String searchType,
                                    @RequestParam("keyword") String keyword,
                                    Model model) {

        log.info("검색 요청: {}, 키워드: {}", searchType, keyword);
        List<InboundProduntDTO> searchResult = adminInboundService.adminSearchInboundList(searchType, keyword);

        Pagination.paginate(model, searchResult, page, "/WEB-INF/views/admin/pages/inbound/inbound-2.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/admin/pages/inbound/inbound-1/search")
    public String adminsearchInboundcheckList(@RequestParam(defaultValue = "1") int page,
                                              @RequestParam("searchType") String searchType,
                                              @RequestParam("keyword") String keyword,
                                              Model model) {

        log.info("검색 요청: {}, 키워드: {}", searchType, keyword);
        List<InboundProduntDTO> searchResult = adminInboundService.adminSearchInboundCheckList(searchType, keyword);

        Pagination.paginate(model, searchResult, page, "/WEB-INF/views/admin/pages/inbound/inbound-1.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping(value = "/admin/pages/inbound/inbound-1/modal-info")
    @ResponseBody
    public List<InboundProduntDTO> getAdminInboundCheck(@RequestParam List<String> prodIds) {
        log.info("🔍 모달용 상품 ID 리스트: {}", prodIds);
        return adminInboundService.getAdminInboundCheck(prodIds);  // productDTO 리스트 반환
    }

    @PostMapping("/admin/pages/inbound/inbound-1/request")
    public ResponseEntity<String> adminrequestInbound(@RequestBody Map<String, Object> request) {
        log.info("🔥 requestInbound() 진입");
        log.info("📦 받은 데이터: {}", request);

        List<String> prodIds = (List<String>) request.get("prodIds");
        List<String> clientIds = (List<String>) request.get("clientIds");
        List<String> quantityStrList = (List<String>) request.get("quantitis");

        if (prodIds == null || clientIds == null || quantityStrList == null
                || prodIds.size() != quantityStrList.size()) {
            log.warn("❌ 유효하지 않은 데이터 형식");
            return ResponseEntity.badRequest().body("Invalid request format");
        }

        try {
            List<Integer> quantities = quantityStrList.stream()
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            adminInboundService.admininsertInboundRequests(prodIds, clientIds, quantities);
            return ResponseEntity.ok("입고 요청 성공");
        } catch (Exception e) {
            log.error("❌ 서버 처리 중 에러 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러");
        }
    }






//        List<Map<String, String>> idPairs = new ArrayList<>();
//        for (int i = 0; i < prodIds.size(); i++) {
//            Map<String, String> pair = new HashMap<>();
//            pair.put("prodId", prodIds.get(i));
//            pair.put("clientId", clientIds.get(i));
//            idPairs.add(pair);
//        }
//
//        try {
//            log.info("✅ 입고 승인 처리 시작...");
//            adminInboundService.admininsertInboundRequests(idPairs);
//            return ResponseEntity.ok("입고 승인 성공");
//        } catch (Exception e) {
//            log.error("❌ 서버 처리 중 에러 발생", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러");
//        }
    }


