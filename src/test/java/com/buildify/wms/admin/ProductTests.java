package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.product.mapper.AdminProductMapper;
import com.wareflow.buildify.domain.admin.product.service.AdminProductService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.ProductVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class ProductTests {
    @Autowired
    AdminProductMapper adminProductMapper;

    @Autowired
    AdminProductService adminProductService;

    @Test
    @Transactional
    @DisplayName("상품 조회 테스트")
    public void adminProductView(){
        List<ProductVO> productVOList = adminProductMapper.adminProductView();
        log.info("조회된 상품 개수: {}", productVOList.size());

        for (ProductVO vo : productVOList) {
            log.info("상품ID: {}, 상품명: {}, 브랜드: {}", vo.getProdId(), vo.getProdName(), vo.getBrand());
        }
    }

    @Test
    @Transactional
    @DisplayName("키워드 검색 정상 동작 테스트")
    public void search(){

        // 검색 키워드 비어 있을 경우
        List<ProductVO> productVOList = adminProductMapper.search("brand","");

        for (ProductVO productVO : productVOList){
            log.info(productVO.getProdName());
        }
        log.info("키워드 비어있을 경우 : " + productVOList.size());

        // 검색 키워드 정상
        List<ProductVO> productVOList2 = adminProductMapper.search("brand","intel");

        for (ProductVO productVO : productVOList2){
            log.info(productVO.getProdName());
        }
        log.info("키워드 검색 : " + productVOList2.size());

        // 검색 키워드 띄어쓰기 포함 될 경우
        List<ProductDTO> productDTOList = adminProductService.search("brand","  iNTeL   ");

        for (ProductDTO productDTO : productDTOList){
            log.info(productDTO.getProdName());
        }
        log.info("키워드 검색(띄어쓰기 포함) : " + productDTOList.size());

        //성공 하면 테스트 키워드 비어있는 경우 검색 검증 OK
        Assertions.assertEquals( productDTOList.size(), productVOList2.size());


    }

    @Test
    @DisplayName("상품 삭제 테스트")
    @Transactional
    public void adminProductRemove(){

        List<ProductVO> productVOList = new ArrayList<>();

        ProductVO productVO = ProductVO.builder()
                .prodId("PROD-063-DDD")
                .build();
        productVOList.add(productVO);

        ProductVO productVO2 = ProductVO.builder()
                .prodId("PROD-064-EEE")
                .build();
        productVOList.add(productVO2);

        int rows = adminProductMapper.adminProductRemove(productVOList);

        log.info("성공 : "+rows);

    }
}
