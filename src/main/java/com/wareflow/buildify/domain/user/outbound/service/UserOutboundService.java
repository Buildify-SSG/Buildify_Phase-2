package com.wareflow.buildify.domain.user.outbound.service;

import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.OutboundDTO;

import java.util.List;

public interface UserOutboundService {

    List<OutboundDTO> outboundList();
}
