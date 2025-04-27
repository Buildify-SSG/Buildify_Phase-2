package com.wareflow.buildify.domain.admin.outbound.mapper;

import com.wareflow.buildify.dto.AdminOutboundRequestDTO;
import com.wareflow.buildify.dto.InventoryDTO;
import com.wareflow.buildify.dto.OutboundDTO;
import com.wareflow.buildify.dto.OutboundInventoryDTO;
import com.wareflow.buildify.vo.OutboundInventoryVO;
import com.wareflow.buildify.vo.OutboundVO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminOutboundMapper {
    List<OutboundInventoryVO> outboundCheckList();

    List<OutboundVO> adminOutboundList();

    List<OutboundInventoryDTO> adminOutboundCheckInfo(@Param("outboundIds") List<String> outboundIds);



    Integer updateInventory(AdminOutboundRequestDTO dto);
    Integer updateOutbound(AdminOutboundRequestDTO dto);
    Integer updateUserWarehouse(  AdminOutboundRequestDTO dto );

}
