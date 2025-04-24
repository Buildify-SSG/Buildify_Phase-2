package com.wareflow.buildify.domain.user.product.mapper;

import com.wareflow.buildify.vo.CategoryVO;
import com.wareflow.buildify.vo.ProductVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insertProduct(ProductVO productVO);
    List<CategoryVO> selectCategoryList();
    String selectCategoryId(@Param("level1") String level1,
                            @Param("level2") String level2,
                            @Param("level3") String level3);
    List<ProductVO> selectProductList(@Param("clientId") String clientId);
}
