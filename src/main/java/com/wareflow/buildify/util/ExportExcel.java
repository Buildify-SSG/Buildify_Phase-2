package com.wareflow.buildify.util;

import com.github.ckpoint.toexcel.core.ToWorkBook;
import com.github.ckpoint.toexcel.core.ToWorkSheet;
import com.github.ckpoint.toexcel.core.type.ToWorkBookType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;


@Component
public class ExportExcel {

    public <T> void exportExcel(List<T> data, HttpServletResponse response, String fileName){

        // 1. 워크북 만들기 (라이브러리에서 제공하는 방식 or 직접 만든 유틸)
        ToWorkBook workBook = new ToWorkBook(ToWorkBookType.XSSF);
        ToWorkSheet sheet = workBook.createSheet();
        sheet.from(data);

        // 2. 파일 다운로드 설정
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+".xlsx\"");

        try (OutputStream os = response.getOutputStream()) {
            workBook.write(os); // 바로 HTTP 응답으로 출력
        } catch (IOException e) {
            throw new RuntimeException("엑셀 다운로드 실패", e);
        }
    }

}
