package com.wareflow.buildify.domain.admin.systemOperation.service;

import com.wareflow.buildify.cache.WarehouseLeaseList;
import com.wareflow.buildify.domain.admin.systemOperation.mapper.AdminWarehouseLeaseMapper;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.dto.WarehouseLeaseDTO;
import com.wareflow.buildify.vo.ProductVO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.rmi.dgc.Lease;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// 관리자 - 창고 계약 관리 서비스 구현체
@Service
@Log4j2
@RequiredArgsConstructor
public class AdminWarehouseLeaseServiceImpl implements AdminWarehouseLeaseService {

    private final AdminWarehouseLeaseMapper adminWarehouseLeaseMapper;

    // 유저 계약정보 가져오기(싱글톤 캐싱)
    @Override
    public List<WarehouseLeaseDTO> getUserLeaseInfo() {

        WarehouseLeaseList.getInstance(adminWarehouseLeaseMapper.getUserLeaseInfo());

        // 싱글톤 캐싱 리스트 가져오기
        List<WarehouseLeaseDTO> warehouseLeaseDTOList =
                WarehouseLeaseList.getInstance().getWarehouseLeaseList();

        // 싱글톤 캐싱 리스트 비었을 경우 DB -> 싱글톤 저장
        if (warehouseLeaseDTOList.isEmpty()){
            WarehouseLeaseList.getInstance().setWarehouseLeaseList(adminWarehouseLeaseMapper.getUserLeaseInfo());
        }

        // 저장된 싱글톤 캐싱 리스트 가져오기
        warehouseLeaseDTOList =
                WarehouseLeaseList.getInstance().getWarehouseLeaseList();

        // 계약기간이 만료되지 않은 계약 찾아서 리스트에 담기

        WarehouseLeaseList.getInstance().setWarehouseLeaseList(
        warehouseLeaseDTOList.stream()
                .filter(dto -> dto.getEndDate().isAfter(LocalDate.now()))
                .map(dto -> {
                    int remainDays = (int) ChronoUnit.DAYS.between(LocalDate.now(), dto.getEndDate());
                    return dto.toBuilder().remainDays(remainDays).build();
                })
                .toList());
        return  WarehouseLeaseList.getInstance().getWarehouseLeaseList();


    }

    // 유저 계약정보 가져오기(DB)
    @Override
    public List<WarehouseLeaseDTO> getUserLeaseDbInfo() {

        List<WarehouseLeaseDTO> warehouseLeaseDTOList = adminWarehouseLeaseMapper.getUserLeaseInfo();

        return warehouseLeaseDTOList;
    }


    // 계약 변경
    @Override
    @Transactional
    public int modifyLeaseRequests(List<String> clientIds,
                                   List<String> endDates,
                                   List<String> wareIds,
                                   List<String> wareCoords) {

        // 1. 변경 대상 DTO 필터링 및 endDate 수정
        List<WarehouseLeaseDTO> originLeaseList = adminWarehouseLeaseMapper.getUserLeaseInfo();
        List<WarehouseLeaseDTO> modifiedLeaseList = new ArrayList<>();
        for (int i = 0; i < clientIds.size(); i++) {
            for (WarehouseLeaseDTO dto : originLeaseList) {
                if (dto.getClientId().equals(clientIds.get(i)) &&
                    dto.getWareId().equals(wareIds.get(i)) &&
                    dto.getWareCoord().equals(wareCoords.get(i))) {
                    WarehouseLeaseDTO updated = dto.toBuilder()
                            .endDate(LocalDate.parse(endDates.get(i)))
                            .build();
                    modifiedLeaseList.add(updated);
                    break;
                }
            }
        }

        // 2. DTO → VO 변환
        List<UserWareHouseVO> voList = new ArrayList<>();
        for (WarehouseLeaseDTO lease : modifiedLeaseList) {
            String posX = String.valueOf(lease.getWareCoord().charAt(0));
            int posY = Integer.parseInt(String.valueOf(lease.getWareCoord().charAt(1)));

            UserWareHouseVO vo = UserWareHouseVO.builder()
                    .clientID(lease.getClientId())
                    .wareId(lease.getWareId())
                    .warehousePosX(posX)
                    .warehousePosY(posY)
                    .wareEndDate(lease.getEndDate())
                    .build();
            voList.add(vo);
            log.info("서비스 X : {} Y : {}", posX, posY);
        }
        log.info("서비스 리스트 : {}", voList.size());

        // 3. 캐시 동기화 (싱글톤 리스트 갱신)
        WarehouseLeaseList.getInstance(adminWarehouseLeaseMapper.getUserLeaseInfo());
        List<WarehouseLeaseDTO> singletonList = WarehouseLeaseList.getInstance().getWarehouseLeaseList();

        List<WarehouseLeaseDTO> refreshedList = new ArrayList<>();
        for (WarehouseLeaseDTO dto : singletonList) {
            boolean matched = false;
            for (WarehouseLeaseDTO updated : modifiedLeaseList) {
                if (dto.getClientId().equals(updated.getClientId()) &&
                    dto.getWareId().equals(updated.getWareId()) &&
                    dto.getWareCoord().equals(updated.getWareCoord())) {
                    int remainDays = (int) ChronoUnit.DAYS.between(LocalDate.now(), updated.getEndDate());
                    WarehouseLeaseDTO refreshed = dto.toBuilder()
                            .endDate(updated.getEndDate())
                            .remainDays(remainDays)
                            .build();
                    refreshedList.add(refreshed);
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                refreshedList.add(dto);
            }
        }
        WarehouseLeaseList.getInstance().setWarehouseLeaseList(refreshedList);

        // 4. DB 업데이트
        return adminWarehouseLeaseMapper.modifyUserLeaseInfo(voList);
    }

    @Override
    public List<WarehouseLeaseDTO> search(@Param("searchType") String searchType,
                                          @Param("keyword")String keyword) {
        keyword = keyword.trim();

        List<WarehouseLeaseDTO> searchDTOList = new ArrayList<>();

        if (keyword.isEmpty()){
            searchDTOList = adminWarehouseLeaseMapper.getUserLeaseInfo();
        }else {
            searchDTOList = adminWarehouseLeaseMapper.search(searchType,keyword);
        }

        searchDTOList = searchDTOList.stream()
                .filter(dto -> dto.getEndDate().isAfter(LocalDate.now()))
                .map(dto -> {
                    int remainDays = (int) ChronoUnit.DAYS.between(LocalDate.now(), dto.getEndDate());
                    return dto.toBuilder().remainDays(remainDays).build();
                }).toList();



        return searchDTOList;
    }
}
