package com.wareflow.buildify.temp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminTempController {

    @GetMapping("/pages/index")
    public String adminIndex() {
        return "admin/pages/index";
    }

    // employee
    @GetMapping("/pages/userManagement/userManagement-1")
    public String userManagement1() { return "admin/pages/userManagement/userManagement-1"; }

    @GetMapping("/pages/userManagement/userManagement-2")
    public String userManagement2() { return "admin/pages/userManagement/userManagement-2"; }

    @GetMapping("/pages/userManagement/userManagement-3")
    public String userManagement3() { return "admin/pages/userManagement/userManagement-3"; }

    @GetMapping("/pages/userManagement/userManagement-4")
    public String userManagement4() { return "admin/pages/userManagement/userManagement-4"; }

    // inbound
    @GetMapping("/pages/inbound/inbound-1")
    public String inbound1() { return "admin/pages/inbound/inbound-1"; }

    @GetMapping("/pages/inbound/inbound-2")
    public String inbound2() { return "admin/pages/inbound/inbound-2"; }

    @GetMapping("/pages/inbound/inbound-3")
    public String inbound3() { return "admin/pages/inbound/inbound-3"; }

    @GetMapping("/pages/inbound/inbound-4")
    public String inbound4() { return "admin/pages/inbound/inbound-4"; }

    // inventory
    @GetMapping("/pages/inventory/inventory-1")
    public String inventory1() { return "admin/pages/inventory/inventory-1"; }

    @GetMapping("/pages/inventory/inventory-2")
    public String inventory2() { return "admin/pages/inventory/inventory-2"; }

    @GetMapping("/pages/inventory/inventory-3")
    public String inventory3() { return "admin/pages/inventory/inventory-3"; }

    @GetMapping("/pages/inventory/inventory-4")
    public String inventory4() { return "admin/pages/inventory/inventory-4"; }

    // outbound
    @GetMapping("/pages/outbound/outbound-1")
    public String outbound1() { return "admin/pages/outbound/outbound-1"; }

    @GetMapping("/pages/outbound/outbound-2")
    public String outbound2() { return "admin/pages/outbound/outbound-2"; }

    @GetMapping("/pages/outbound/outbound-3")
    public String outbound3() { return "admin/pages/outbound/outbound-3"; }

    @GetMapping("/pages/outbound/outbound-4")
    public String outbound4() { return "admin/pages/outbound/outbound-4"; }

    // product
    @GetMapping("/pages/product/product-1")
    public String product1() { return "admin/pages/product/product-1"; }

    @GetMapping("/pages/product/product-2")
    public String product2() { return "admin/pages/product/product-2"; }

    @GetMapping("/pages/product/product-3")
    public String product3() { return "admin/pages/product/product-3"; }

    @GetMapping("/pages/product/product-4")
    public String product4() { return "admin/pages/product/product-4"; }

    // systemOperation
    @GetMapping("/pages/systemOperation/systemOperation-1")
    public String systemOperation1() { return "admin/pages/systemOperation/systemOperation-1"; }

    @GetMapping("/pages/systemOperation/systemOperation-2")
    public String systemOperation2() { return "admin/pages/systemOperation/systemOperation-2"; }

    @GetMapping("/pages/systemOperation/systemOperation-3")
    public String systemOperation3() { return "admin/pages/systemOperation/systemOperation-3"; }

    @GetMapping("/pages/systemOperation/systemOperation-4")
    public String systemOperation4() { return "admin/pages/systemOperation/systemOperation-4"; }




}