package com.wareflow.buildify.domain.user.inventory.controller;

import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.InventoryFilterDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class InventoryUserControllerImpl implements InventoryUserController {
    @Override
    public ResponseEntity<List<InventoryDTO>> getUserInventoryList(InventoryFilterDTO filter) {
        return null;
    }
}
