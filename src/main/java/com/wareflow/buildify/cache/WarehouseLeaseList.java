package com.wareflow.buildify.cache;

import com.wareflow.buildify.dto.WarehouseLeaseDTO;

import java.util.List;

public class WarehouseLeaseList {

    private static WarehouseLeaseList instance;
    private List<WarehouseLeaseDTO> warehouseLeaseList;

    public static WarehouseLeaseList getInstance(List<WarehouseLeaseDTO> warehouseLeaseList) {
        if (instance == null) {
            instance = new WarehouseLeaseList(warehouseLeaseList);
        }
        return instance;
    }

    public void setWarehouseLeaseList(List<WarehouseLeaseDTO> warehouseLeaseList) {
        this.warehouseLeaseList = warehouseLeaseList;
    }

    public static WarehouseLeaseList getInstance() {
        if (instance == null) {
            throw new IllegalStateException("WarehouseLeaseList is not initialized. Call getInstance(List<WarehouseLeaseDTO>) first.");
        }
        return instance;
    }

    public WarehouseLeaseList(List<WarehouseLeaseDTO> warehouseLeaseList) {
        this.warehouseLeaseList = warehouseLeaseList;
    }

    public List<WarehouseLeaseDTO> getWarehouseLeaseList() {
        return warehouseLeaseList;
    }


}
