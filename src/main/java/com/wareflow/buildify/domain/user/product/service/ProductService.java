package com.wareflow.buildify.domain.user.product.service;

import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.ProductDTO;

import java.util.List;
import java.util.Map;

public interface ProductService {
    boolean registerProduct(ProductDTO productDTO);
    Map<String, List<String>> getCategoryList();
}
