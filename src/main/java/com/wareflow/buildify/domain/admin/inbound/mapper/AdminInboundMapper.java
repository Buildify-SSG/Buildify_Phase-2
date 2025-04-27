package com.wareflow.buildify.domain.admin.inbound.mapper;

import com.wareflow.buildify.dto.*;
import com.wareflow.buildify.vo.InboundProductVO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.InventoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminInboundMapper {


    List<InboundProductVO> adminInboundList();

    List<InboundProductVO> adminInboundCheckList();

    List<InboundProduntDTO> adminSearchInboundList(@Param("searchType") String searchType, @Param("keyword") String keyword);

    List<InboundProduntDTO> adminSearchInboundCheckList(@Param("searchType") String searchType, @Param("keyword") String keyword);

    List<InboundProduntDTO> getAdminInboundCheck(@Param("inboundIds") List<String> inboundIds);


//    void updateInboundStatus(@Param("prodId") String prodId, @Param("clientId") String clientId);
    void updateInboundStatus(List<InboundVO> vo);


    int insertInventoryIfNotExists(List<InboundVO> vo);

    int postAdminInboundCheckUpdate(InventoryVO vo);

    List<InboundVO> inboundvo();

    List<InboundApproveDTO> inboundApproveList();

    int insert(@Param("list") List<InboundApproveDTO> approveDTOList);

//    Integer adminInboundApprove(@Param("list") List<InboundApproveDTO> dto);

    Integer updateInboundStatus(InboundApproveDTO dto);
    Integer updateInventoryQuantity(InboundApproveDTO dto);
    Integer insertInventoryIfNotExists(InboundApproveDTO dto);
    Integer updateUserWarehouseUsage(InboundApproveDTO dto);



}
