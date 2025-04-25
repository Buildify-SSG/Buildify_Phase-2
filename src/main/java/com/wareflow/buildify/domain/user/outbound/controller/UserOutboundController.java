package com.wareflow.buildify.domain.user.outbound.controller;

import com.wareflow.buildify.domain.user.outbound.service.UserOutboundService;
import com.wareflow.buildify.dto.OutboundDTO;
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
public class UserOutboundController {
    private final UserOutboundService userOutboundService;

    @GetMapping("/user/pages/outbound/outbound-2")
    public String outboundList(@RequestParam(defaultValue = "1") int page, Model model){
        log.info("아웃바운드리스트컨트롤러");
        List<OutboundDTO> dtoList = userOutboundService.outboundList();
        Pagination.paginate(model, dtoList, page, "/WEB-INF/views/users/pages/outbound/outbound-2.jps");
        return "users/layouts/userlayout";
    }





}
