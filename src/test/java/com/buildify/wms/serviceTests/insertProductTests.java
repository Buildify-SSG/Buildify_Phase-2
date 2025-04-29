package com.buildify.wms.serviceTests;

import com.wareflow.buildify.constants.IdPrefix;
import com.wareflow.buildify.domain.user.product.service.ProductService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.util.CustomIdGenerator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/root-context.xml")
@Log4j2
public class insertProductTests {

    @Autowired
    private ProductService productService;

    @Test
    @Transactional
    public void testInsertProduct() {
        ProductDTO productDTO = ProductDTO.builder()
                .brand("Nike")
                .prodName("에어 줌 페가수스 40")
                .prodPrice(159000)
                .prodCode((int) (Math.random() * 100000)) // ❗ 중복 방지용 임의값
                .prodSize(new BigDecimal("270.3"))        // 예: 270.3 cm³
                .prodCategoryid((CustomIdGenerator.generateId(IdPrefix.CAT)))
                .clientId((CustomIdGenerator.generateId(IdPrefix.USR)))
                .build();

        productService.registerProduct(productDTO);
    }
}
