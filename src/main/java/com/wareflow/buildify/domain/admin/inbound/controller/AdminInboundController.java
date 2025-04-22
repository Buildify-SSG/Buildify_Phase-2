package com.wareflow.buildify.domain.admin.inbound.controller;

import com.wareflow.buildify.domain.admin.inbound.service.AdminInboundService;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
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
}
