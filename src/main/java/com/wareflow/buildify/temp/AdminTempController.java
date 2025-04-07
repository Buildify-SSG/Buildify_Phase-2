package com.wareflow.buildify.temp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminTempController {

    @GetMapping("/index")
    public String adminIndex() {
        return "admin/index"; // templates/admin/index.html
    }

    @GetMapping("/pages-1-1")
    public String adminpage1() {
        return "admin/pages-1-1"; // templates/admin/index.html
    }

    @GetMapping("/pages-1-2")
    public String adminpage1_2() {
        return "admin/pages-1-2";
    }

    @GetMapping("/pages/sample")
    public String maniPage(Model model) {
        model.addAttribute("content", "page/sample :: content");
        return "admin/common/layouts/defaultLayout"; // 이거 주의
    }
}