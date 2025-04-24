package com.wareflow.buildify.domain.user.inbound.mapper;

import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.InboundProductVO;
import com.wareflow.buildify.vo.InboundVO;
import com.wareflow.buildify.vo.ProductVO;
import com.wareflow.buildify.vo.UserWareHouseVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserInboundMapper {

    /**
     회원 입고현황 출력
     */
    List<ProductVO> inboundList(ProductVO vo);

    /**
     * 회원 상품리스트 조회 (회원등록상품)
     */
    List<InboundProductVO> inboundInsertlist(InboundProductVO vo);

    /**
     * 회원 입고요청
     */
//    List<InboundVO> inboundInsert();

    List<InboundProduntDTO> searchInboundList(@Param("searchType") String searchType, @Param("keyword") String keyword);

    List<ProductDTO> searchInboundInsertList(@Param("searchType") String searchType, @Param("keyword") String keyword);

    List<InboundDTO> inboundInsert();

    List<ProductDTO> getInboundInsert(@Param("prodId") List<String> prodId);

    int insertInbound(InboundVO vo);

    List<UserWareHouseVO> insertware(InboundProductVO vo);
}