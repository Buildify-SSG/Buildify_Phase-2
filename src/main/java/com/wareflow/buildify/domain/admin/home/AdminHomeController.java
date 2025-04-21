package com.wareflow.buildify.domain.user.home;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/home")
public class AdminHomeController {

//    private final InboundService inboundService;
//    private final OutboundService outboundService;
//    private final InventoryService inventoryService;

    @GetMapping
    public String home(@AuthenticationPrincipal CustomUserDetails user, Model model) {
        // 타 도메인 서비스 계층의 기능을 들여와서 대시보드 보여주면 될듯
//        int todayInbound = inboundService.countToday(user.getClientId());
//        int todayOutbound = outboundService.countToday(user.getClientId());
//        int totalStock = inventoryService.getTotalStock(user.getClientId());
//
//        model.addAttribute("inboundCount", todayInbound);
//        model.addAttribute("outboundCount", todayOutbound);
//        model.addAttribute("stock", totalStock);

        return "admin/home";
    }
}
