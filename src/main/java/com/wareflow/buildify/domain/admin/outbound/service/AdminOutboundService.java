package com.wareflow.buildify.domain.admin.outbound.service;

import com.wareflow.buildify.dto.AdminOutboundRequestDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.OutboundInventoryDTO;

import java.util.List;

public interface AdminOutboundService {

    List<OutboundInventoryDTO> outboundCheckList();

    List<OutboundDTO> adminOutboundList();

    List<OutboundInventoryDTO> adminOutboundCheckInfo(List<String > outboundIds);

    void adminOutnboundRequests(List<AdminOutboundRequestDTO> list);

}
