package com.wareflow.buildify.domain.admin.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminHomeController {

    @GetMapping("/pages/index")
    public String adminIndex(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/index.jsp");
        return "admin/layouts/adminlayout";
    }

    // userManagement
//    @GetMapping("/pages/userManagement/userManagement-1")
//    public String userManagement1(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/admin/pages/userManagement/userManagement-1.jsp");
//        return "admin/layouts/adminlayout";
//    }

    @GetMapping("/pages/userManagement/userManagement-2")
    public String userManagement2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/userManagement/userManagement-2.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/userManagement/userManagement-3")
    public String userManagement3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/userManagement/userManagement-3.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/userManagement/userManagement-4")
    public String userManagement4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/userManagement/userManagement-4.jsp");
        return "admin/layouts/adminlayout";
    }

//    @GetMapping("/pages/inbound/inbound-1")
//    public String inbound1(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/admin/pages/inbound/inbound-1.jsp");
//        return "admin/layouts/adminlayout";
//    }
//
//    @GetMapping("/pages/inbound/inbound-2")
//    public String inbound2(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/admin/pages/inbound/inbound-2.jsp");
//        return "admin/layouts/adminlayout";
//    }

    @GetMapping("/pages/inbound/inbound-3")
    public String inbound3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/inbound/inbound-3.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/inbound/inbound-4")
    public String inbound4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/inbound/inbound-4.jsp");
        return "admin/layouts/adminlayout";
    }


//    @GetMapping("/pages/inventory/inventory-1")
//    public String inventory1(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/admin/pages/inventory/inventory-1.jsp");
//        return "admin/layouts/adminlayout";
//    }

    @GetMapping("/pages/inventory/inventory-2")
    public String inventory2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/inventory/inventory-2.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/inventory/inventory-3")
    public String inventory3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/inventory/inventory-3.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/inventory/inventory-4")
    public String inventory4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/inventory/inventory-4.jsp");
        return "admin/layouts/adminlayout";
    }

    // outbound
    @GetMapping("/pages/outbound/outbound-1")
    public String outbound1(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/outbound/outbound-1.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/outbound/outbound-2")
    public String outbound2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/outbound/outbound-2.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/outbound/outbound-3")
    public String outbound3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/outbound/outbound-3.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/outbound/outbound-4")
    public String outbound4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/outbound/outbound-4.jsp");
        return "admin/layouts/adminlayout";
    }

    // product
//    @GetMapping("/pages/product/product-1")
//    public String product1(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/admin/pages/product/product-1.jsp");
//        return "admin/layouts/adminlayout";
//    }

    @GetMapping("/pages/product/product-2")
    public String product2(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/product/product-2.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/product/product-3")
    public String product3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/product/product-3.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/product/product-4")
    public String product4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/product/product-4.jsp");
        return "admin/layouts/adminlayout";
    }

    // systemOperation
//    @GetMapping("/pages/systemOperation/systemOperation-1")
//    public String systemOperation1(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/admin/pages/systemOperation/systemOperation-1.jsp");
//        return "admin/layouts/adminlayout";
//    }

//    @GetMapping("/pages/systemOperation/systemOperation-2")
//    public String systemOperation2(Model model) {
//        model.addAttribute("body", "/WEB-INF/views/admin/pages/systemOperation/systemOperation-2.jsp");
//        return "admin/layouts/adminlayout";
//    }

    @GetMapping("/pages/systemOperation/systemOperation-3")
    public String systemOperation3(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/systemOperation/systemOperation-3.jsp");
        return "admin/layouts/adminlayout";
    }

    @GetMapping("/pages/systemOperation/systemOperation-4")
    public String systemOperation4(Model model) {
        model.addAttribute("body", "/WEB-INF/views/admin/pages/systemOperation/systemOperation-4.jsp");
        return "admin/layouts/adminlayout";
    }

}