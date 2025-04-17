package com.wareflow.buildify.domain.admin.product.service;

import com.wareflow.buildify.domain.admin.product.mapper.AdminProductMapper;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.vo.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// 관리자 - 상품조회 서비스 구현체
@Service
@Log4j2
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService{

    private final AdminProductMapper adminProductMapper;

    // 관리자 상품 전체 조회
    @Override
    public List<ProductDTO> adminProductView() {

        List<ProductVO> productVOList =adminProductMapper.adminProductView();

        List<ProductDTO> productDTOList = new ArrayList<>();
        for (int i = 0; i < productVOList.size(); i++) {
            ProductDTO productDTO = ProductDTO.builder().
                    prodId(productVOList.get(i).getProdId())
                    .brand(productVOList.get(i).getBrand())
                    .prodName(productVOList.get(i).getProdName())
                    .prodPrice(productVOList.get(i).getProdPrice())
                    .prodCode(productVOList.get(i).getProdCode())
                    .prodSize(productVOList.get(i).getProdSize())
                    .prodCategoryid(productVOList.get(i).getProdCategoryid())
                    .clientId(productVOList.get(i).getClientId())
                    .build();
            productDTOList.add(productDTO);
        }
        return productDTOList;
    }

    // 검색
    @Override
    public List<ProductDTO> search(String searchType,String keyword) {

        keyword = keyword.trim();

        List<ProductVO> searchVOList = new ArrayList<>();

        if (keyword.isEmpty()){
            searchVOList = adminProductMapper.adminProductView();
        }else {
            searchVOList = adminProductMapper.search(searchType,keyword);
        }

        List<ProductDTO> searchlist = new ArrayList<>();

        for (int i = 0; i < searchVOList.size(); i++) {
            ProductDTO productDTO = ProductDTO.builder().
                    prodId(searchVOList.get(i).getProdId())
                    .brand(searchVOList.get(i).getBrand())
                    .prodName(searchVOList.get(i).getProdName())
                    .prodPrice(searchVOList.get(i).getProdPrice())
                    .prodCode(searchVOList.get(i).getProdCode())
                    .prodSize(searchVOList.get(i).getProdSize())
                    .prodCategoryid(searchVOList.get(i).getProdCategoryid())
                    .clientId(searchVOList.get(i).getClientId())
                    .build();
            searchlist.add(productDTO);
        }
        return searchlist;
    }

    // 관리자 상품 삭제
    @Override
    public int adminProductRemove(List<String > productDTOList) {

        log.info("서비스 : dto 수량"+productDTOList.size());

        List<ProductVO> productVOList = new ArrayList<>();
        for(String productDTO : productDTOList){
        ProductVO productVO = ProductVO.builder()
                .prodId(productDTO)
                .build();
            productVOList.add(productVO);
        }
        int rows = adminProductMapper.adminProductRemove(productVOList);

        return rows;
    }
}
