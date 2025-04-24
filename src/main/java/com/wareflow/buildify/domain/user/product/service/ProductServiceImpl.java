package com.wareflow.buildify.domain.user.product.service;

import com.wareflow.buildify.constants.IdPrefix;
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
    public Map<String, List<String>> getCategoryList() {
        List<CategoryVO> categoryVOList = productMapper.selectCategoryList();

        List<String> categoryLevel1 = categoryVOList.stream()
                .map(CategoryVO::getCategoryLevel1)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        List<String> categoryLevel2 = categoryVOList.stream()
                .map(CategoryVO::getCategoryLevel2)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        List<String> categoryLevel3 = categoryVOList.stream()
                .map(CategoryVO::getCategoryLevel3)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        Map<String, List<String>> result = new HashMap<>();
        result.put("categoryLevel1", categoryLevel1);
        result.put("categoryLevel2", categoryLevel2);
        result.put("categoryLevel3", categoryLevel3);

        return result;

    }

}
