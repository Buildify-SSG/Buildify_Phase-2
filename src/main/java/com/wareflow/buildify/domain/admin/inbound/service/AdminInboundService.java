package com.wareflow.buildify.domain.admin.inbound.service;

import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.InboundProductVO;

import java.util.List;
import java.util.Map;

public interface AdminInboundService {

    List<InboundProduntDTO> AdminInboundList();

    List<InboundProduntDTO> AdminInboundCheckList();

    List<InboundProduntDTO> adminSearchInboundList(String searchType, String keyword);

    List<InboundProduntDTO> adminSearchInboundCheckList(String searchType, String keyword);

    List<InboundProduntDTO> getAdminInboundCheck(List<String > prodId);

    void updateInboundStatus(List<String> prodIds, List<String > clientIds );
    void admininsertInboundRequests(List<String> prodIds, List<String > clientIds, List<Integer > quantitis, List<String> wareIds);
}
