package com.wareflow.buildify.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserTempController {

    @GetMapping("/pages/index")
    public String index() {
        return "users/pages/index";
    }

    @GetMapping("/pages/inbound/inbound-1")
    public String inbound1() {
        return "users/pages/inbound/inbound-1";
    }

    @GetMapping("/pages/inbound/inbound-2")
    public String inbound2() {
        return "users/pages/inbound/inbound-2";
    }

    @GetMapping("/pages/inbound/inbound-3")
    public String inbound3() {
        return "users/pages/inbound/inbound-3";
    }

    @GetMapping("/pages/inbound/inbound-4")
    public String inbound4() {
        return "users/pages/inbound/inbound-4";
    }


    @GetMapping("/pages/inventory/inventory-1")
    public String inventory1() {
        return "users/pages/inventory/inventory-1";
    }

    @GetMapping("/pages/inventory/inventory-2")
    public String inventory2() {
        return "users/pages/inventory/inventory-2";
    }

    @GetMapping("/pages/inventory/inventory-3")
    public String inventory3() {
        return "users/pages/inventory/inventory-3";
    }

    @GetMapping("/pages/inventory/inventory-4")
    public String inventory4() {
        return "users/pages/inventory/inventory-4";
    }

    @GetMapping("/pages/outbound/outbound-1")
    public String outbound1() {
        return "users/pages/outbound/outbound-1";
    }

    @GetMapping("/pages/outbound/outbound-2")
    public String outbound2() {
        return "users/pages/outbound/outbound-2";
    }

    @GetMapping("/pages/outbound/outbound-3")
    public String outbound3() {
        return "users/pages/outbound/outbound-3";
    }

    @GetMapping("/pages/outbound/outbound-4")
    public String outbound4() {
        return "users/pages/outbound/outbound-4";
    }

    @GetMapping("/pages/product/product-1")
    public String product1() {
        return "users/pages/product/product-1";
    }

    @GetMapping("/pages/product/product-2")
    public String product2() {
        return "users/pages/product/product-2";
    }

    @GetMapping("/pages/product/product-3")
    public String product3() {
        return "users/pages/product/product-3";
    }

    @GetMapping("/pages/product/product-4")
    public String product4() {
        return "users/pages/product/product-4";
    }


    @GetMapping("/pages/userWarehouse/userWarehouse-1")
    public String userWarehouse1() {
        return "users/pages/userWarehouse/userWarehouse-1";
    }

    @GetMapping("/pages/userWarehouse/userWarehouse-2")
    public String userWarehouse2() {
        return "users/pages/userWarehouse/userWarehouse-2";
    }

    @GetMapping("/pages/userWarehouse/userWarehouse-3")
    public String userWarehouse3() {
        return "users/pages/userWarehouse/userWarehouse-3";
    }

    @GetMapping("/pages/userWarehouse/userWarehouse-4")
    public String userWarehouse4() {
        return "users/pages/userWarehouse/userWarehouse-4";
    }

}