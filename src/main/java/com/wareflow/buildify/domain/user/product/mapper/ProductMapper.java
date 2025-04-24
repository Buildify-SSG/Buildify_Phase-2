package com.wareflow.buildify.domain.user.product.mapper;

import com.wareflow.buildify.vo.CategoryVO;
import com.wareflow.buildify.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insertProduct(ProductVO productVO);
    List<CategoryVO> selectCategoryList();
}
