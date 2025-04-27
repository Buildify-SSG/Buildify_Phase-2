package com.wareflow.buildify.domain.admin.inbound.service;

import com.wareflow.buildify.domain.admin.inbound.mapper.AdminInboundMapper;
import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.inbound.mapper.UserInboundMapper;
import com.wareflow.buildify.dto.*;
import com.wareflow.buildify.vo.InboundProductVO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.InventoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.LocalTime.now;


@Service
@Log4j2
@RequiredArgsConstructor
public class AdminInboundServiceImpl implements AdminInboundService {
    private final AdminInboundMapper adminInboundMapper;

    @Override
    public List<InboundProduntDTO> AdminInboundList() {
        log.info("asdfsssss");
        List<InboundProductVO> vo = adminInboundMapper.adminInboundList();

        List<InboundProduntDTO> dtoList = new ArrayList<>();
        for (InboundProductVO inboundProductVO : vo) {
            InboundProduntDTO inboundProduntDTO = InboundProduntDTO.builder()
                    .clientId(inboundProductVO.getClientId())
                    .prodId(inboundProductVO.getProdId())
                    .prodName(inboundProductVO.getProdName())
                    .quantity(inboundProductVO.getQuantity())
                    .reqInboundDate(inboundProductVO.getReqInboundDate())
                    .inboundProcessDate(inboundProductVO.getInboundProcessDate())
                    .wareId(inboundProductVO.getWareId())
                    .inboundStatus(inboundProductVO.getInboundStatus())
                    .warehousePosX(inboundProductVO.getWarehousePosX())
                    .warehousePosY(inboundProductVO.getWarehousePosY())
                    .prodSize(inboundProductVO.getProdSize())
                    .build();
            dtoList.add(inboundProduntDTO);
        }
        return dtoList;
    }

    @Override
    public List<InboundProduntDTO> AdminInboundCheckList() {
        List<InboundProductVO> vo = adminInboundMapper.adminInboundCheckList();
        List<InboundProduntDTO> dtoList = new ArrayList<>();

        for (InboundProductVO inboundProductVO : vo) {
            InboundProduntDTO inboundProduntDTO = InboundProduntDTO.builder()
                    .clientId(inboundProductVO.getClientId())
                    .prodId(inboundProductVO.getProdId())
                    .prodName(inboundProductVO.getProdName())
                    .quantity(inboundProductVO.getQuantity())
                    .reqInboundDate(inboundProductVO.getReqInboundDate())
                    .inboundProcessDate(inboundProductVO.getInboundProcessDate())
                    .wareId(inboundProductVO.getWareId())
                    .inboundStatus(inboundProductVO.getInboundStatus())
                    .inboundId(inboundProductVO.getInboundId())
                    .warehousePosX(inboundProductVO.getWarehousePosX())
                    .warehousePosY(inboundProductVO.getWarehousePosY())
                    .prodSize(inboundProductVO.getProdSize())
                    .build();
            dtoList.add(inboundProduntDTO);
        }
        return dtoList;
    }

    @Override
    public List<InboundProduntDTO> adminSearchInboundList(String searchType, String keyword) {
        return adminInboundMapper.adminSearchInboundList(searchType, "%" + keyword + "%");
    }

    @Override
    public List<InboundProduntDTO> adminSearchInboundCheckList(String searchType, String keyword) {
        return adminInboundMapper.adminSearchInboundCheckList(searchType, "%" + keyword + "%");
    }

    @Override
    public List<InboundProduntDTO> getAdminInboundCheck(List<String> inboundIds) {
        return adminInboundMapper.getAdminInboundCheck(inboundIds);
    }


//    @Override
//    public void updateInboundStatus(List<String> prodIds, List<String> clientIds) {
//        log.info("어드민 승인 서비스");
//        // 입고 상태를 1로 변경
//        for (int i = 0; i < prodIds.size(); i++) {
//            String prodId = prodIds.get(i);
//            String clientId = clientIds.get(i);
//
//            // Mapper 메서드는 각각 String을 받기 때문에 이렇게 하나씩 넘겨야 함
//            adminInboundMapper.updateInboundStatus(prodId, clientId);
//        }
//
//
//    }


//    public int adminInsert(List<InboundApproveDTO> approveDTOList){
//        List<InboundApproveDTO> inboundApproveDTOList = new ArrayList<>();
//
//        // 수량 * 사이즈
//        // = 이 물건의 총사이즈?
//
//        for (d d : d) {
//            InboundApproveDTO inboundApproveDTO = InboundApproveDTO.builder().build();
//            inboundApproveDTOList.add(inboundApproveDTO);
//        }
//
//        int result = adminInboundMapper.insert(approveDTOList);
//        return result;
//    }

    // 1. List 새로만들기
    // 2. 리스트에 필요한애들 담기(필요하면 DTO 새로만들기?)
    // 3. list.stream.filter inbound_id 프론트에서 받은거 일치하는 애들만 남기기
    // 4.


    @Override
    public void admininsertInboundRequests(List<InboundApproveDTO> inboundIds) {
        log.info("어드민 승인 ");
//
//        List<InboundApproveDTO> inboundApproveDTOList = adminInboundMapper.inboundApproveList();
//        List<InboundApproveDTO> resultList = new ArrayList<>();
//
//        log.info("서비스 매퍼 사이즈 {}",inboundApproveDTOList.size());
//
//
//        for (InboundApproveDTO inboundApproveDTO : inboundApproveDTOList){
//            if (inboundIds.contains(inboundApproveDTO.getInboundId())){
//                resultList.add(inboundApproveDTO);
//            }
//        }
//
//        log.info("서비스 빌더 리스트 사이즈{}",resultList.size());
//
//        resultList.stream().map(dto -> {
//            BigDecimal size = dto.getProdSize().multiply(BigDecimal.valueOf(dto.getQuantity()));
//            return dto.toBuilder().newUsage(size).build();
//        }).toList();
//
//        resultList.stream()
//                .filter(dto -> dto.getInventoryId() == null)
//                .map(dto -> {
//            String inventoryId = "INB-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
//            return dto.toBuilder().inventoryId(inventoryId).build();
//        }).toList();
//
//        log.info("서비스 빌더 리스트 사이즈2 {}",resultList.size());
//
//        for (int i = 0; i < resultList.size(); i++) {
//            log.info("서비스 인바운드 id : {}",resultList.get(i).getInboundId());
//        }
//        log.info(inboundApproveDTOList);
        for(InboundApproveDTO inboundApproveDTO : inboundIds) {
            adminInboundMapper.updateInboundStatus(inboundApproveDTO);
            adminInboundMapper.updateInventoryQuantity(inboundApproveDTO);
            adminInboundMapper.insertInventoryIfNotExists(inboundApproveDTO);
            adminInboundMapper.updateUserWarehouseUsage(inboundApproveDTO);
        }





        }

    }


