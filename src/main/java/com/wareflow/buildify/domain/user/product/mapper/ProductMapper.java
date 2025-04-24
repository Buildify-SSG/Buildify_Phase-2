package com.wareflow.buildify.domain.user.product.mapper;

import com.wareflow.buildify.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int insertProduct(ProductVO productVO);
}
