package com.wareflow.buildify.domain.user.outbound.service;

import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.OutboundInventoryDTO;

import java.util.List;

public interface UserOutboundService {

    List<OutboundInventoryDTO> outboundInsertList();
    List<OutboundDTO> outboundList();

    List<OutboundInventoryDTO> outboundInsertInfo(List<String > outboundIds);

    void insertOutnboundRequests(List<InventoryDTO> list );

}
