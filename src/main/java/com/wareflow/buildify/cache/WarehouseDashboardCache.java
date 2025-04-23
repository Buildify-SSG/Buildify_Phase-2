package com.wareflow.buildify.cache;

import com.wareflow.buildify.dto.WareHouseDashBoardDTO;
import org.springframework.stereotype.Component;

import java.util.List;

public class WarehouseDashboardCache {

    private static WarehouseDashboardCache instance = new WarehouseDashboardCache();
    private List<WareHouseDashBoardDTO> wareHouseDashBoard;

    public static WarehouseDashboardCache getInstance(List<WareHouseDashBoardDTO> wareHouseDashBoard) {if (instance == null) {
            instance = new WarehouseDashboardCache();
        }
        return instance;
    }

    public void setWareHouseDashBoardCache(List<WareHouseDashBoardDTO> wareHouseDashBoard) {
        this.wareHouseDashBoard = wareHouseDashBoard;
    }

    public static WarehouseDashboardCache getInstance() {
        if (instance == null) {
            throw new IllegalStateException("WarehouseList is not initialized. Call getInstance(List<wareHouseDashBoardDTO>) first.");
        }
        return instance;
    }

    public WarehouseDashboardCache() {
    }

    public List<WareHouseDashBoardDTO> getWareHouseDashBoardCache() {
        return wareHouseDashBoard;
    }


}
