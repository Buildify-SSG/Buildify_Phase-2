package com.buildify.wms.serviceTests;

import com.wareflow.buildify.domain.user.product.mapper.ProductMapper;
import com.wareflow.buildify.domain.user.product.service.ProductService;
import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.vo.CategoryVO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/root-context.xml"
})
@Log4j2
public class getCategoryListTests {
    @Autowired
    private ProductService productService;

    @Test
    public void testSelectCategoryList(){
        Map<String, List<String>> testlist = productService.getCategoryList();
        testlist.forEach((key, valueList) -> {
            System.out.println("🔹 분류: " + key);
            for (String value : valueList) {
                System.out.println("   - " + value);
            }
        });
    }

}
