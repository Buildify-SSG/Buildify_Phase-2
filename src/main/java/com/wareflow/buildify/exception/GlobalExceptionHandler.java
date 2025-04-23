package com.wareflow.buildify.exception;

import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointer(Exception e, Model model) {
        model.addAttribute("errorMessage", "예상치 못한 문제가 발생했습니다.");
        return "common/pages/errorPage/commonError"; // 공통 에러 페이지로 이동
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handle405(Exception e) {
        return "common/pages/errorPage/errorpage405"; // 405 페이지로 이동
    }

    @ExceptionHandler(Exception.class)
    public String handleAll(Exception e) {
        return "common/pages/errorPage/errorpage500"; // 나머지는 500 처리
    }
}