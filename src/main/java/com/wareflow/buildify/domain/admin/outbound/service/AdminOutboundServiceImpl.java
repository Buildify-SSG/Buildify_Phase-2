package com.wareflow.buildify.domain.admin.outbound.service;

import com.wareflow.buildify.domain.admin.inbound.mapper.AdminInboundMapper;
import com.wareflow.buildify.domain.admin.outbound.mapper.AdminOutboundMapper;
import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.dto.AdminOutboundRequestDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.OutboundInventoryDTO;
import com.wareflow.buildify.vo.OutboundInventoryVO;
import com.wareflow.buildify.vo.OutboundVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class AdminOutboundServiceImpl implements AdminOutboundService{
    private final AdminOutboundMapper adminOutboundMapper;
    @Override
    public List<OutboundInventoryDTO> outboundCheckList() {
        List<OutboundInventoryVO> voList = adminOutboundMapper.outboundCheckList();
        log.info("아웃바운드인서트리스트2222");
        log.info("아웃바운드인서트리스트 사이즈: {}", voList.size());

        List<OutboundInventoryDTO> dtoList = new ArrayList<>();
        for (OutboundInventoryVO outboundInventoryVO : voList) {
            OutboundInventoryDTO outboundInventoryDTO = OutboundInventoryDTO.builder()
                    .outboundId(outboundInventoryVO.getOutboundId())
                    .prodId(outboundInventoryVO.getProdId())
                    .clientId(outboundInventoryVO.getClientId())
                    .quantity(outboundInventoryVO.getQuantity())
                    .wareId(outboundInventoryVO.getWareId())
                    .warehousePosX(outboundInventoryVO.getWarehousePosX())
                    .warehousePosY(outboundInventoryVO.getWarehousePosY())
                    .prodName(outboundInventoryVO.getProdName())
                    .prodPrice(outboundInventoryVO.getProdPrice())
                    .prodSize(outboundInventoryVO.getProdSize())
                    .inventoryId(outboundInventoryVO.getInventoryId())
                    .build();
            dtoList.add(outboundInventoryDTO);
        }
        return dtoList;

    }

    @Override
    public List<OutboundDTO> adminOutboundList() {
        log.info("아웃바운드리스트 서비스");

        List<OutboundVO> vo = adminOutboundMapper.adminOutboundList();

        List<OutboundDTO> dtoList = new ArrayList<>();
        for (OutboundVO outboundVOVO : vo) {
            OutboundDTO outboundDTO = OutboundDTO.builder()
                    .clientId(outboundVOVO.getClientId() )
                    .prodName(outboundVOVO.getProdName())
                    .quantity(outboundVOVO.getQuantity())
                    .reqOutboundDate(outboundVOVO.getReqOutboundDate())
                    .outboundProcessDate(outboundVOVO.getOutboundProcessDate())
                    .wareId(outboundVOVO.getWareId())
                    .status(outboundVOVO.getStatus())
                    .build();
            dtoList.add(outboundDTO);
        }
        return dtoList;
    }

    @Override
    public List<OutboundInventoryDTO> adminOutboundCheckInfo(List<String> outboundIds) {
        log.info("유저 아웃바운드 모달인포 서비스");
        return adminOutboundMapper.adminOutboundCheckInfo(outboundIds);

    }

    @Override
    public void adminOutnboundRequests(List<AdminOutboundRequestDTO> list) {
        log.info("유저 아웃바운드 리퀘스트 서비스 진입");

        for (AdminOutboundRequestDTO dto : list) {
            adminOutboundMapper.updateInventory(dto);
            adminOutboundMapper.updateOutbound(dto);
            adminOutboundMapper.updateUserWarehouse(dto);
        }
    }


}
