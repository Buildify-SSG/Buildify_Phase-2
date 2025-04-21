package com.wareflow.buildify.domain.user.inbound.controller;

import com.wareflow.buildify.domain.user.inbound.service.UserInboundService;
import com.wareflow.buildify.domain.user.inbound.service.UserInboundServiceImpl;
import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("")
@Log4j2
@RequiredArgsConstructor


public class UserInboundController {
    private final UserInboundService userInboundService;
    @GetMapping("/users/pages/inbound/inbound-1")
    public String  inboundlist(@RequestParam(defaultValue = "1") int page, Model model){
        List<ProductDTO> dtoList = userInboundService.inboundList();
        log.info("sjfalsdjf");
//        model.addAttribute("body","/WEB-INF/views/users/pages/inbound/inbound-1");
        log.info(":흰색_확인_표시: body: {}", model.getAttribute("body"));
        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/users/pages/inbound/inbound-1.jsp");
        return "users/layouts/userlayout";
//        model.addAttribute("body","/WEB-INF/views/users/pages/inbound/inbound-1");
//        log.info(":흰색_확인_표시: body: {}", model.getAttribute("body"));
//        return "/users/layouts/userlayout";
    }

//    @GetMapping("/users/pages/inbound/inbound-2")
//    public String    inboundInsertlist(@RequestParam(defaultValue = "1") int page, Model model){
//        log.info("inboundinsertlist........");
//        List<InboundDTO> dtoList = userInboundService.inboundInsertlist();
////        model.addAttribute("dtoList",dtoList);
//        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/users/pages/inbound/inbound-2.jsp");
//        return "users/layouts/userlayout";
//    }

    @GetMapping("/users/pages/inbound/inbound-2")
    public String    inboundInsertlist(@RequestParam(defaultValue = "1") int page, Model model){

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


    @GetMapping("/insert")
    public void inboundInsertGet(){

    }
    @PostMapping("/insert")
    public void inboundInsertPost( ){

    }
}