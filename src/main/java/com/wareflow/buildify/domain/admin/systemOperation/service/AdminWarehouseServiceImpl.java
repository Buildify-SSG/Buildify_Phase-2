package com.wareflow.buildify.domain.admin.systemOperation.service;

import com.wareflow.buildify.domain.admin.systemOperation.mapper.AdminWarehouseMapper;
import com.wareflow.buildify.dto.WareHouseDTO;
import com.wareflow.buildify.dto.WarehouseViewDTO;
import com.wareflow.buildify.util.GeoUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

// 📦 AdminWarehouseServiceImpl: 관리자 창고 레이아웃 및 정보 서비스 구현체
// Service implementation for managing warehouse layout and info used by admin features
@Service
@Log4j2
@RequiredArgsConstructor
public class AdminWarehouseServiceImpl implements AdminWarehouseService{

    private final AdminWarehouseMapper adminWarehouseMapper;

    /**
     * 🗺️ 창고 레이아웃 맵 조회
     * Retrieves warehouse layout mapping by wareId and coordinate (coord).
     *
     * @return Map<창고ID, Map<좌표, 창고 상세 리스트>>
     */
    @Override
    public Map<String, Map<String,List<WarehouseViewDTO>>> getWarehouseList() {

        List<WarehouseViewDTO> viewDTOList = adminWarehouseMapper.getWarehouseList();
        log.info("서비스 창고 정보 가져오기 : " + viewDTOList.size());

        Map<String,Map<String,List<WarehouseViewDTO>>> layoutmap = new HashMap<>();

        for (WarehouseViewDTO dto : viewDTOList) {
            String wareId = dto.getWareId(); // W001
            String coord = dto.getWareCoord(); // A1
            // 창고 ID(W001) 및 좌표(A1)를 기준으로 그룹화
            layoutmap
                    .computeIfAbsent(wareId, k -> new HashMap<>())
                    .computeIfAbsent(coord, k -> new ArrayList<>())
                    .add(dto);
        }
        log.info("서비스 map 사이즈 : "+layoutmap.size());

        return layoutmap;
    }

    /**
     * 📊 창고 정보 리스트 조회 + 위경도 및 사용률 계산 포함
     * Fetches warehouse list and calculates usage rate and geolocation info.
     */
    @Override
    public List<WareHouseDTO> getWarehouseInfo() {

        List<WareHouseDTO> dtoList = adminWarehouseMapper.getWarehouseInfo();
        List<WareHouseDTO> wareHouseDTOList = new ArrayList<>();

        for (WareHouseDTO wareHouseDTO : dtoList){

            // 사용률 계산 = 가용 공간 / 전체 공간 (소수점 4자리 반올림)
            BigDecimal usageRate = wareHouseDTO.getWareAvailSpace()
                    .divide(wareHouseDTO.getWareTotalSize(), 4, RoundingMode.HALF_UP);

            // 주소를 위도/경도로 변환
            double[] doubles = GeoUtil.getLatLngFromAddress(wareHouseDTO.getWareAddress());
            double lat = doubles != null ? doubles[0] : 0.0;
            double lng = doubles != null ? doubles[1] : 0.0;

            // 위 정보들을 바탕으로 DTO 재조립
            WareHouseDTO dto = WareHouseDTO.builder()
                    .wareId(wareHouseDTO.getWareId())
                    .wareName(wareHouseDTO.getWareName())
                    .wareAddress(wareHouseDTO.getWareAddress())
                    .lat(lat)
                    .lng(lng)
                    .wareAdminName(wareHouseDTO.getWareAdminName())
                    .wareTotalSize(wareHouseDTO.getWareTotalSize())
                    .wareAvailSpace(wareHouseDTO.getWareAvailSpace())
                    .usageRate(usageRate)
                    .lastInboundDate(wareHouseDTO.getLastInboundDate())
                    .lastOutboundDate(wareHouseDTO.getLastOutboundDate())
                    .lat(lat)
                    .lng(lng)
                    .build();
            wareHouseDTOList.add(dto);
        }
        return wareHouseDTOList;
    }

}
