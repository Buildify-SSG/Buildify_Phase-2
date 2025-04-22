package com.wareflow.buildify.domain.auth.signup.controller;

import com.wareflow.buildify.domain.auth.signup.service.SignupService;
import com.wareflow.buildify.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Log4j2
public class SignupController {

    private SignupService signupService;

    @GetMapping("/signup")
    public String signup() {
        return "/signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute UserDTO userDTO, RedirectAttributes rttr) {

        if(signupService.isUserIdExist(userDTO.getUserId())) {
            rttr.addFlashAttribute("msg", "이미 사용 중인 아이디입니다.");
            rttr.addFlashAttribute("user", userDTO);
            return "redirect:/signup";
        }

        boolean result = signupService.signUp(userDTO);

        if (result) {
            rttr.addFlashAttribute("msg", "회원가입 성공!");
            signupService.addAuth(userDTO.getUserId(), 0);
            log.info("----------------- : " + userDTO.getUserId());
            return "redirect:/login";
        } else {
            rttr.addFlashAttribute("msg", "회원가입 실패");
            rttr.addFlashAttribute("user", userDTO);
            return "redirect:/signup";
        }
    }
}
