package com.wareflow.buildify.domain.user.outbound.service;

import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.outbound.mapper.UserOutboundMapper;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.vo.InboundProductVO;
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
public class UserOutboundServiceImpl implements UserOutboundService{
    private final UserOutboundMapper userOutboundMapper;



    @Override
    public List<OutboundDTO> outboundList() {
        log.info("아웃바운드리스트 서비스");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();

        String a = userDetails.getClientId();



        List<OutboundVO> vo = userOutboundMapper.outboundlist(a);

        List<OutboundDTO> dtoList = new ArrayList<>();
        for (OutboundVO outboundVOVO : vo) {
            OutboundDTO outboundDTO = OutboundDTO.builder()
                    .clientId(outboundVOVO.getOutboundId() )
                    .prodName(outboundVOVO.getProdName())
                    .quantity(outboundVOVO.getQuantity())
                    .reqOutboundDate(outboundVOVO.getReqOutboundDate())
                    .outboundProcessDate(outboundVOVO.getOutboundProcessDate())
                    .status(outboundVOVO.getStatus())
                    .build();
            dtoList.add(outboundDTO);
        }
        return dtoList;
    }

}

