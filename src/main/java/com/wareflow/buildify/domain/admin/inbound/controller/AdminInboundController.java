package com.wareflow.buildify.domain.admin.inbound.controller;

import com.wareflow.buildify.domain.admin.inbound.service.AdminInboundService;
import com.wareflow.buildify.dto.InboundApproveDTO;
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
    public List<InboundProduntDTO> getAdminInboundCheck(@RequestParam List<String> inboundIds) {
        log.info("🔍 모달용 상품 ID 리스트: {}", inboundIds);
        return adminInboundService.getAdminInboundCheck(inboundIds);  // productDTO 리스트 반환
    }


//    public void adminrequestInbound(List<String> inboundIds) {
//        log.info("🔥 requestInbound() 진입");
//        for (int i = 0; i < inboundIds.size(); i++) {
//            log.info("📦 받은 데이터: {}", inboundIds);
//        }
//            adminInboundService.admininsertInboundRequests(inboundIds);
//    }

    @RequestMapping(value = "/admin/pages/inbound/inbound-1/request", method = {RequestMethod.GET , RequestMethod.POST})
    public ResponseEntity<String> adminrequestInbound(@RequestBody Map<String, Object> request) {
        log.info("🔥 requestInbound() 진입");
        for (int i = 0; i < request.size(); i++) {
            log.info("📦 받은 데이터: {}", request);
        }

        List<String> inboundIds =  (List<String>) request.get("inboundIds");

        List<InboundApproveDTO> requestList = new ArrayList<>();

        for (int i = 0; i < inboundIds.size(); i++) {
            InboundApproveDTO dto = new InboundApproveDTO();
            dto.setInboundId(inboundIds.get(i));
            requestList.add(dto);
        }
        try {
            adminInboundService.admininsertInboundRequests(requestList);
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


