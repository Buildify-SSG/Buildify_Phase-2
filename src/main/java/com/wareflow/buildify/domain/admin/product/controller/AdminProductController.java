package com.wareflow.buildify.domain.admin.product.controller;

import com.github.ckpoint.toexcel.core.ToWorkBook;
import com.github.ckpoint.toexcel.core.ToWorkSheet;
import com.github.ckpoint.toexcel.core.type.ToWorkBookType;
import com.wareflow.buildify.domain.admin.product.service.AdminProductService;
import com.wareflow.buildify.dto.ProductDTO;
import com.wareflow.buildify.util.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;



// 관리자-상품조회 컨트롤러 구현체
@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/admin/pages/product")
public class AdminProductController {

    private final AdminProductService adminProductService;


    // 관리자 상품 전체 조회
    @GetMapping("/product-1")
    public String adminProductView(@RequestParam(defaultValue = "1") int page,Model model) {

        List<ProductDTO> productList = adminProductService.adminProductView();

        Pagination.paginate(model, productList, page,"/WEB-INF/views/admin/pages/product/product-1.jsp");
        return "admin/layouts/adminlayout";
    }

    // 검색
    @GetMapping("/product-1/search")
    public String searchProduct(@RequestParam(defaultValue = "1") int page,
                                @RequestParam("searchType") String searchType,
                                @RequestParam("keyword") String keyword,
                                Model model) {

        log.debug("🔍 검색조건 - type: {}, keyword: {}", searchType, keyword);

        List<ProductDTO> productDTOList = adminProductService.search(searchType,keyword);
        log.debug("🔍 검색결과 수: {}", productDTOList.size());

        model.addAttribute("productList",productDTOList);

        Pagination.paginate(model, productDTOList, page,"/WEB-INF/views/admin/pages/product/product-1.jsp");
        return "admin/layouts/adminlayout";
    }

    // 관리자 상품 삭제
    @PostMapping("/product-1/api/productRemove")
    public String adminProductRemove(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam("selectedIndexes") List<String> selectedIndexes,
                                     RedirectAttributes redirect,
                                     Model model) {
        log.warn("🔥🔥🔥 adminProductRemove");
        log.info("컨트롤러 인덱스 : "+selectedIndexes.size());

        int rows = adminProductService.adminProductRemove(selectedIndexes);
        log.info(rows);
        String msg = "상품 " + rows + "개 삭제 성공하였습니다.";
        List<ProductDTO> productDTOList = adminProductService.adminProductView();

        redirect.addFlashAttribute("msg", msg);
        model.addAttribute("body","/admin/pages/product/product-1");
        return "redirect:/admin/pages/product/product-1?page=1";
    }

    @GetMapping("/product-1/api/excel")
    public void adminProductExportExcel(HttpServletResponse response) {
        // 1. 데이터 준비 (보통은 서비스에서 가져옴)
        List<ProductDTO> data = adminProductService.adminProductView();

        // 2. 워크북 만들기 (라이브러리에서 제공하는 방식 or 직접 만든 유틸)
        ToWorkBook workBook = new ToWorkBook(ToWorkBookType.XSSF);
        ToWorkSheet sheet = workBook.createSheet();
        sheet.from(data);

        // 3. 파일 다운로드 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\"adminProductList.xlsx\"");

        try (OutputStream os = response.getOutputStream()) {
            workBook.write(os); // 바로 HTTP 응답으로 출력
        } catch (IOException e) {
            throw new RuntimeException("엑셀 다운로드 실패", e);
        }
    }

}
