package com.wareflow.buildify.domain.admin.inbound.service;

import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.InboundProductVO;

import java.util.List;

public interface AdminInboundService {

    List<InboundProduntDTO> AdminInboundList();

    List<InboundProduntDTO> AdminInboundCheckList();
}
