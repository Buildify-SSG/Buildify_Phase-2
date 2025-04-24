package com.wareflow.buildify.domain.user.inbound.service;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.inbound.mapper.UserInboundMapper;
import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.InboundRequestDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.InboundProductVO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional

public class UserInboundServiceImpl implements UserInboundService {
    private final UserInboundMapper userInboundMapper;

    @Override
    public List<ProductDTO> inboundList() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        log.info("인바운드1서비스");
        List<ProductVO> vo = userInboundMapper.inboundList();
        List<ProductDTO> dtoList = new ArrayList<>();
        for(ProductVO productVO:vo){
            ProductDTO productDTO = ProductDTO.builder()
                    .prodId(productVO.getProdId())
                    .prodName(productVO.getProdName())
                    .prodPrice(productVO.getProdPrice())
                    .prodSize(productVO.getProdSize())
                    .clientId(userDetails.getClientId())
                    .build();
            dtoList.add(productDTO);

        }
        return dtoList;

    }

    @Override
    public List<InboundProduntDTO> inboundInsertlist() {
        List<InboundProductVO> vo = userInboundMapper.inboundInsertlist();
        List<InboundProduntDTO> dtoList = new ArrayList<>();
        for(InboundProductVO inboundProductVO:vo){
            InboundProduntDTO inboundProduntDTO = InboundProduntDTO.builder()
                    .clientId(inboundProductVO.getClientId())
                    .prodName(inboundProductVO.getProdName())
                    .quantity(inboundProductVO.getQuantity())
                    .reqInboundDate(inboundProductVO.getReqInboundDate())
                    .inboundProcessDate(inboundProductVO.getInboundProcessDate())
                    .inboundStatus(inboundProductVO.getInboundStatus())
                    .wareId(inboundProductVO.getWareId())
                    .build();
            dtoList.add(inboundProduntDTO);
        }
        return dtoList;
    }

    @Override
    public List<InboundVO> inboundInsert() {
        return null;
    }

    @Override
    public List<InboundProduntDTO> searchInboundList(String searchType, String keyword) {
        return userInboundMapper.searchInboundList(searchType, "%" + keyword + "%");
    }

    @Override
    public List<ProductDTO> searchInboundInsertList(String searchType, String keyword) {
        return userInboundMapper.searchInboundInsertList(searchType, "%" + keyword + "%");
    }

    @Override
    public List<ProductDTO> getInboundInsert(List<String> prodId) {
        return userInboundMapper.getInboundInsert(prodId);
    }

    public void insertInboundRequests(List<String> prodIds, List<Integer> quantities) {
        log.info("여기까진오니");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        if (userDetails == null) {
            log.error("❌ userDetails가 null입니다.");
        } else {
            log.info("✅ userDetails 객체 타입: {}", userDetails.getClass().getName());
            log.info("✅ userDetails.getUsername(): {}", userDetails.getUsername());
            log.info("✅ userDetails.getClientId(): {}", userDetails.getClientId());
        }

        for (int i = 0; i < prodIds.size(); i++) {
            InboundVO vo = new InboundVO();
            String uniqueId = "INB-" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
                    + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            vo.setInboundId(uniqueId);
            vo.setProdId(prodIds.get(i));
            vo.setClientId(userDetails.getClientId());
            vo.setQuantity(quantities.get(i));
            vo.setInboundStatus(0); // 상태: 대기
            vo.setReqInboundDate(Date.valueOf(LocalDate.now()));

            int result = userInboundMapper.insertInbound(vo);
            // 로그로 insert 결과 확인
            log.info("🧾 insert 실행 결과: {} (inboundId={}, prodId={}, quantity={})",
                    result, vo.getInboundId(), vo.getProdId(), vo.getQuantity());
        }
    }


}