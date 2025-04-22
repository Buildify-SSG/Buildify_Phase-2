package com.wareflow.buildify.domain.user.inbound.service;

import com.wareflow.buildify.dto.InboundDTO;
import com.wareflow.buildify.dto.InboundProduntDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.InboundVO;

import java.util.List;

public interface UserInboundService {

    /**
     회원 입고현황 출력
     */
    List<ProductDTO>  inboundList();

    /**
     * 회원 상품리스트 조회 (회원등록상품)
     */
//    List<InboundDTO> inboundInsertlist();

    List<InboundProduntDTO> inboundInsertlist();

    /**
     * 회원 입고요청
     */
    List<InboundVO> inboundInsert();

    /**
     * 검색
     */
    List<InboundProduntDTO> searchInboundList(String searchType, String keyword);

    List<ProductDTO> searchInboundInsertList(String searchType, String keyword);

//    void requestInbound(String userId, List<String> prodIds, List<Integer> quantities);

    void requestInbound(List<String> prodIds, List<Integer> quantities);
}