package com.wareflow.buildify.temp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserTempController {

    @GetMapping("/pages/index")
    public String adminIndex(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/index.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/inbound/inbound-1")
    public String inbound1(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/inbound/inbound-1.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/inbound/inbound-2")
    public String inbound2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/inbound/inbound-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/inbound/inbound-3")
    public String inbound3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/inbound/inbound-3.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/inbound/inbound-4")
    public String inbound4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/inbound/inbound-4.jsp");
        return "users/layouts/userlayout";
    }

    // inventory
//    @GetMapping("/pages/inventory/inventory-1")
//    public String inventory1(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/users/pages/inventory/inventory-1.jsp");
//        return "users/layouts/userlayout";
//    }

    @GetMapping("/pages/inventory/inventory-2")
    public String inventory2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/inventory/inventory-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/inventory/inventory-3")
    public String inventory3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/inventory/inventory-3.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/inventory/inventory-4")
    public String inventory4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/inventory/inventory-4.jsp");
        return "users/layouts/userlayout";
    }

    // outbound
    @GetMapping("/pages/outbound/outbound-1")
    public String outbound1(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/outbound/outbound-1.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/outbound/outbound-2")
    public String outbound2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/outbound/outbound-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/outbound/outbound-3")
    public String outbound3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/outbound/outbound-3.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/outbound/outbound-4")
    public String outbound4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/outbound/outbound-4.jsp");
        return "users/layouts/userlayout";
    }

    // product
    @GetMapping("/pages/product/product-1")
    public String product1(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/product/product-1.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/product/product-2")
    public String product2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/product/product-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/product/product-3")
    public String product3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/product/product-3.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/product/product-4")
    public String product4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/product/product-4.jsp");
        return "users/layouts/userlayout";
    }

    // userWarehouse
    @GetMapping("/pages/userWarehouse/userWarehouse-1")
    public String userWarehouse1(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/userWarehouse/userWarehouse-1.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/userWarehouse/userWarehouse-2")
    public String userWarehouse2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/userWarehouse/userWarehouse-2.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/userWarehouse/userWarehouse-3")
    public String userWarehouse3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/userWarehouse/userWarehouse-3.jsp");
        return "users/layouts/userlayout";
    }

    @GetMapping("/pages/userWarehouse/userWarehouse-4")
    public String userWarehouse4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/users/pages/userWarehouse/userWarehouse-4.jsp");
        return "users/layouts/userlayout";
    }

}