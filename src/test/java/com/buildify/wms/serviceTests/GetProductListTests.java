package com.buildify.wms.serviceTests;

import com.wareflow.buildify.domain.user.product.service.ProductService;
import com.wareflow.buildify.dto.ProductDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/root-context.xml"
})
@Log4j2
public class GetProductListTests {

    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    public void getProductList() {
       List<ProductDTO> productDTOList = productService.getProductList();
        log.info("productDTOList: {}", productDTOList);
    }

}
