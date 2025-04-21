package com.buildify.wms.serviceTests;


import com.wareflow.buildify.domain.admin.inventory.service.InventoryAdminService;
import com.wareflow.buildify.domain.user.inventory.service.InventoryUserService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class InventoryServiceTests {
    @Autowired(required = false)
    InventoryUserService inventoryUserService;

    @Autowired(required = false)
    InventoryAdminService inventoryAdminService;

    @Test
    public void testInventoryUserService() {
        inventoryUserService.getUserInventory();
    }

    @Test
    public void testInventoryAdminService(){
        inventoryAdminService.getAdminInventory();

    }

}
