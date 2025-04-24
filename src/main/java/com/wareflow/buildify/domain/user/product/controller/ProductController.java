package com.wareflow.buildify.domain.user.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wareflow.buildify.domain.user.product.service.ProductService;
import com.wareflow.buildify.dto.CategoryDTO;
import com.wareflow.buildify.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("users/pages")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;

    @GetMapping("product/product-1")
    public String showInsertProductForm(Model model) throws JsonProcessingException {
        Map<String, List<String>> categoryMap = productService.getCategoryList();
        ObjectMapper objectMapper = new ObjectMapper();
        String categoryJson = objectMapper.writeValueAsString(categoryMap);
        log.info("categoryJson: {}", categoryJson);
        model.addAttribute("categoryJson", categoryJson);  // ✅ 이걸 추가
        model.addAttribute("body", "/WEB-INF/views/users/pages/product/product-1.jsp");
        return "users/layouts/userlayout";
    }



    @PostMapping("product/product-1")
    public String insertProduct(@ModelAttribute ProductDTO productDTO, RedirectAttributes rttr) {
        log.info("상품 등록 시도: {}", productDTO);

        boolean result = productService.registerProduct(productDTO);
        if (result) {
            rttr.addFlashAttribute("msg", "상품이 성공적으로 등록되었습니다!");
        } else {
            rttr.addFlashAttribute("msg", "상품 등록에 실패했습니다.");
        }

        return "redirect:/users/pages/product/product-1";
    }
}
