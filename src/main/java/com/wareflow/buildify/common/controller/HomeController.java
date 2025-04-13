package com.wareflow.buildify.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/") // 루트페이지
    public String index() {
        return "index"; // WEB-INF/views/index.jsp로 이동
    }
}
