package com.buildify.wms.admin;

import com.wareflow.buildify.domain.admin.product.mapper.AdminProductMapper;
import com.wareflow.buildify.vo.ProductVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class ProductTests {
    @Autowired
    AdminProductMapper adminProductMapper;

    @Test
    public void adminProductView(){
        List<ProductVO> productVOList = adminProductMapper.adminProductView();
        log.info("조회된 상품 개수: {}", productVOList.size());

        for (ProductVO vo : productVOList) {
            log.info("상품ID: {}, 상품명: {}, 브랜드: {}", vo.getProdId(), vo.getProdName(), vo.getBrand());
        }
    }

    @Test
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


    }

    @Test
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
