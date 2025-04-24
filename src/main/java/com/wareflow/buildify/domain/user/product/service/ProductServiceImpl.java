package com.wareflow.buildify.domain.user.product.service;

import com.wareflow.buildify.constants.IdPrefix;
import com.wareflow.buildify.domain.auth.login.security.CustomUserDetails;
import com.wareflow.buildify.domain.user.product.mapper.ProductMapper;
import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.util.CustomIdGenerator;
import com.wareflow.buildify.vo.CategoryVO;
import com.wareflow.buildify.vo.ProductVO;
import com.wareflow.buildify.vo.UserVO;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Lazy;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    @Override
    public boolean registerProduct(ProductDTO productDTO) {

        String clientId = ((CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getClientId();
        productDTO.setClientId(clientId);

        ProductVO productVO = ProductVO.builder()
                .prodId(CustomIdGenerator.generateId(IdPrefix.PRD))
                .brand(productDTO.getBrand())
                .prodName(productDTO.getProdName())
                .prodPrice(productDTO.getProdPrice())
                .prodCode(productDTO.getProdCode())
                .prodSize(productDTO.getProdSize())
                .prodCategoryid(productDTO.getProdCategoryid())
                .clientId(productDTO.getClientId())
                .build();

        return productMapper.insertProduct(productVO) == 1;
    }

    @Override
    public Map<String, Map<String, List<String>>> getCategoryList() {
        List<CategoryVO> list = productMapper.selectCategoryList();

        Map<String, Map<String, List<String>>> result = new LinkedHashMap<>();

        for (CategoryVO vo : list) {
            String level1 = vo.getCategoryLevel1();
            String level2 = vo.getCategoryLevel2();
            String level3 = vo.getCategoryLevel3();

            result
                    .computeIfAbsent(level1, k -> new LinkedHashMap<>())
                    .computeIfAbsent(level2, k -> new ArrayList<>())
                    .add(level3);
        }

        return result;
    }

    @Override
    public String getCategoryId(String categoryLevel1, String categoryLevel2, String categoryLevel3) {
        return productMapper.selectCategoryId(categoryLevel1, categoryLevel2, categoryLevel3);
    }

}
