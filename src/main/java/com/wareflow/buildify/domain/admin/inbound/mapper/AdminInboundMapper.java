package com.wareflow.buildify.domain.admin.inbound.mapper;

import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.ProductDTO;
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

    List<InboundProduntDTO> getAdminInboundCheck(@Param("prodId") List<String> prodId);


    void updateInboundStatus(@Param("prodId") String prodId, @Param("clientId") String clientId);

    int postAdminInboundCheckUpdate(InventoryVO vo);



}
