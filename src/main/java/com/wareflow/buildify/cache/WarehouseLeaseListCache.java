package com.wareflow.buildify.cache;

import com.wareflow.buildify.dto.WarehouseLeaseDTO;

import java.util.List;

public class WarehouseLeaseListCache {

    private static WarehouseLeaseListCache instance;
    private List<WarehouseLeaseDTO> warehouseLeaseList;

    public static WarehouseLeaseListCache getInstance(List<WarehouseLeaseDTO> warehouseLeaseList) {
        if (instance == null) {
            instance = new WarehouseLeaseListCache(warehouseLeaseList);
        }
        return instance;
    }

    public void setWarehouseLeaseList(List<WarehouseLeaseDTO> warehouseLeaseList) {
        this.warehouseLeaseList = warehouseLeaseList;
    }

    public static WarehouseLeaseListCache getInstance() {
        if (instance == null) {
            throw new IllegalStateException("WarehouseLeaseList is not initialized. Call getInstance(List<WarehouseLeaseDTO>) first.");
        }
        return instance;
    }

    public WarehouseLeaseListCache(List<WarehouseLeaseDTO> warehouseLeaseList) {
        this.warehouseLeaseList = warehouseLeaseList;
    }

    public List<WarehouseLeaseDTO> getWarehouseLeaseList() {
        return warehouseLeaseList;
    }


}
