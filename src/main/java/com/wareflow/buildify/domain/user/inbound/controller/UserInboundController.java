package com.wareflow.buildify.domain.user.inbound.controller;

import com.wareflow.buildify.domain.user.inbound.service.UserInboundService;
import com.wareflow.buildify.domain.user.inbound.service.UserInboundServiceImpl;
import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.InboundRequestDTO;
import com.wareflow.buildify.dto.ProductDTO;
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
import java.util.stream.Collectors;


@Controller
@RequestMapping("")
@Log4j2
@RequiredArgsConstructor


public class UserInboundController {
    private final UserInboundService userInboundService;

    @GetMapping("/users/pages/inbound/inbound-1")
    public String inboundlist(@RequestParam(defaultValue = "1") int page, Model model) {
        List<ProductDTO> dtoList = userInboundService.inboundList();
        log.info("인바운드1 리스트");
        log.info(":흰색_확인_표시: body: {}", model.getAttribute("body"));

        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/users/pages/inbound/inbound-1.jsp");

        return "users/layouts/userlayout";

    }


    @GetMapping("/users/pages/inbound/inbound-2")
    public String inboundInsertlist(@RequestParam(defaultValue = "1") int page, Model model) {

        log.info("inboundinsertlist........");
        List<InboundProduntDTO> dtoList = userInboundService.inboundInsertlist();
        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/users/pages/inbound/inbound-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/users/pages/inbound/inbound-2/search")
    public String searchInboundList(@RequestParam(defaultValue = "1") int page,
                                    @RequestParam("searchType") String searchType,
                                    @RequestParam("keyword") String keyword,
                                    Model model) {

        log.info("검색 요청: {}, 키워드: {}", searchType, keyword);
        List<InboundProduntDTO> searchResult = userInboundService.searchInboundList(searchType, keyword);

        Pagination.paginate(model, searchResult, page, "/WEB-INF/views/users/pages/inbound/inbound-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/users/pages/inbound/inbound-1/search")
    public String searchInboundInsertList(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam("searchType") String searchType,
                                          @RequestParam("keyword") String keyword,
                                          Model model) {

        log.info("검색 요청: {}, 키워드: {}", searchType, keyword);
        List<ProductDTO> searchResult = userInboundService.searchInboundInsertList(searchType, keyword);

        Pagination.paginate(model, searchResult, page, "/WEB-INF/views/users/pages/inbound/inbound-1.jsp");
        return "users/layouts/userlayout";
    }


    @GetMapping("/users/pages/inbound/inbound-1/modal-info")
    @ResponseBody
    public List<ProductDTO> getInboundInsert(@RequestParam List<String> prodIds) {
        log.info("🔍 모달용 상품 ID 리스트: {}", prodIds);
        return userInboundService.getInboundInsert(prodIds);  // productDTO 리스트 반환
    }

    @PostMapping("/users/pages/inbound/inbound-1/request")
    public ResponseEntity<String> requestInbound(@RequestBody Map<String, Object> request) {
        log.info("🔥 requestInbound() 진입");
        log.info("📦 받은 데이터: {}", request);

        List<String> prodIds = (List<String>) request.get("prodIds");
        List<Integer> quantities = (List<Integer>) request.get("quantities");

        if (prodIds == null || quantities == null || prodIds.size() != quantities.size()) {
            log.warn("❌ 유효하지 않은 데이터 형식");
            return ResponseEntity.badRequest().body("Invalid request format");
        }

        try {
            // 실제 처리 로직
            log.info("✅ 입고 등록 처리 시작...");
             userInboundService.insertInboundRequests(prodIds, quantities);
             log.info(prodIds+","+quantities);

            return ResponseEntity.ok("입고 요청 성공");
        } catch (Exception e) {
            log.error("❌ 서버 처리 중 에러 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 에러");
        }
    }


}