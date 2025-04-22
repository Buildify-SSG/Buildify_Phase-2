package com.wareflow.buildify.domain.admin.inbound.service;

import com.wareflow.buildify.domain.admin.inbound.mapper.AdminInboundMapper;
import com.wareflow.buildify.domain.user.inbound.mapper.UserInboundMapper;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.vo.InboundProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        for(InboundProductVO inboundProductVO:vo){
            InboundProduntDTO inboundProduntDTO = InboundProduntDTO.builder()
                    .clientId(inboundProductVO.getClientId())
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



}

