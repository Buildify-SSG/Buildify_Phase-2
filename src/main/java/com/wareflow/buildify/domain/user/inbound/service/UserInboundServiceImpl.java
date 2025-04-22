package com.wareflow.buildify.domain.user.inbound.service;

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
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor

public class UserInboundServiceImpl implements UserInboundService {
    private final UserInboundMapper userInboundMapper;

    @Override
    public List<ProductDTO> inboundList() {
        List<ProductVO> vo = userInboundMapper.inboundList();
        List<ProductDTO> dtoList = new ArrayList<>();
        for(ProductVO productVO:vo){
            ProductDTO productDTO = ProductDTO.builder()
                    .prodId(productVO.getProdId())
                    .prodName(productVO.getProdName())
                    .prodPrice(productVO.getProdPrice())
                    .prodSize(productVO.getProdSize())
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
    public void requestInbound(List<String> prodIds, List<Integer> quantities) {
        for (int i = 0; i < prodIds.size(); i++) {
            log.info("인서트서비스");
            InboundVO inbound = new InboundVO();
            inbound.setProdId(prodIds.get(i));
            inbound.setQuantity(quantities.get(i));
            inbound.setInboundStatus(0); // 대기
//            inbound.setReqInboundDate(LocalDate.now());
            log.info("➡️ INSERT 요청: {}", inbound);

            userInboundMapper.insertInbound(inbound);
        }
    }
}