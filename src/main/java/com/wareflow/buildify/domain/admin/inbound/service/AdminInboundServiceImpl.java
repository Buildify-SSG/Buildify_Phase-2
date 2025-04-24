package com.wareflow.buildify.domain.admin.inbound.service;

import com.wareflow.buildify.domain.admin.inbound.mapper.AdminInboundMapper;
import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.inbound.mapper.UserInboundMapper;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.InboundProductVO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.InventoryVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Map;


@Service
@Log4j2
@RequiredArgsConstructor
public class AdminInboundServiceImpl implements AdminInboundService {
    private final AdminInboundMapper adminInboundMapper;
    @Override
    public List<InboundProduntDTO> AdminInboundList(){
        List<InboundProductVO> vo = adminInboundMapper.adminInboundList();

        List<InboundProduntDTO> dtoList = new ArrayList<>();
        for(InboundProductVO inboundProductVO:vo){
            InboundProduntDTO inboundProduntDTO = InboundProduntDTO.builder()
                    .clientId(inboundProductVO.getClientId())
                    .prodId(inboundProductVO.getProdId())
                    .prodName(inboundProductVO.getProdName())
                    .quantity(inboundProductVO.getQuantity())
                    .reqInboundDate(inboundProductVO.getReqInboundDate())
                    .inboundProcessDate(inboundProductVO.getInboundProcessDate())
                    .wareId(inboundProductVO.getWareId())
                    .inboundStatus(inboundProductVO.getInboundStatus())
                    .build();
            dtoList.add(inboundProduntDTO);
        }
        return dtoList;
    }

    @Override
    public List<InboundProduntDTO> AdminInboundCheckList(){
        List<InboundProductVO> vo = adminInboundMapper.adminInboundCheckList();
        List<InboundProduntDTO> dtoList = new ArrayList<>();
        for (InboundProductVO VO : vo) {
            log.info("prodId = " + VO.getProdId());
        }
        for(InboundProductVO inboundProductVO:vo){
            InboundProduntDTO inboundProduntDTO = InboundProduntDTO.builder()
                    .clientId(inboundProductVO.getClientId())
                    .prodId(inboundProductVO.getProdId())
                    .prodName(inboundProductVO.getProdName())
                    .quantity(inboundProductVO.getQuantity())
                    .reqInboundDate(inboundProductVO.getReqInboundDate())
                    .inboundProcessDate(inboundProductVO.getInboundProcessDate())
                    .wareId(inboundProductVO.getWareId())
                    .inboundStatus(inboundProductVO.getInboundStatus())
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
    public List<InboundProduntDTO> getAdminInboundCheck(List<String> prodId) {
        return adminInboundMapper.getAdminInboundCheck(prodId);
    }


    @Override
    public void updateInboundStatus(List<String> prodIds, List<String > clientIds) {
        log.info("어드민 승인 서비스");
            // 입고 상태를 1로 변경
        for (int i = 0; i < prodIds.size(); i++) {
            String prodId = prodIds.get(i);
            String clientId = clientIds.get(i);

            // Mapper 메서드는 각각 String을 받기 때문에 이렇게 하나씩 넘겨야 함
            adminInboundMapper.updateInboundStatus(prodId, clientId);
        }



}

    @Override
    public void admininsertInboundRequests(List<String> prodIds, List<String> clientIds, List<Integer> quantitis) {
        log.info("어드민 승인 업데이트 서비스");
        for (int i = 0; i < prodIds.size(); i++) {
            InventoryVO vo = new InventoryVO();
            String uniqueId = "INB-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                    + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            vo.setInventoryId(uniqueId);
            vo.setProdId(prodIds.get(i));
            vo.setClientId(clientIds.get(i));
            vo.setQuantity(quantitis.get(i));
            vo.setLastInboundDate(Date.valueOf(LocalDate.now()));

            int result = adminInboundMapper.postAdminInboundCheckUpdate(vo);
            // 로그로 insert 결과 확인
            log.info("🧾 insert 실행 결과: {} (inboundId={}, prodId={}, quantity={})",
                    result, vo.getClientId(), vo.getProdId(), vo.getQuantity());
        }
    }

    private String generateInboundId() {
        return "INB-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

}
