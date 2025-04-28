package com.wareflow.buildify.domain.user.outbound.mapper;

import com.wareflow.buildify.dto.InboundApproveDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.OutboundInventoryDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserOutboundMapper {

    List<OutboundInventoryVO> outboundInsertList(OutboundInventoryVO vo);
    List<OutboundVO> outboundlist(String a);

    List<OutboundInventoryDTO> outboundInsertInfo(@Param("inventoryId") List<String> inventoryId);

    List<UserWareHouseVO> insertOutbound(String  vo);

    Integer insertOutnboundRequest(InventoryDTO dto);

}
