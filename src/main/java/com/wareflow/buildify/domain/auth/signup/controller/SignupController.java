package com.wareflow.buildify.domain.auth.signup.controller;

import com.wareflow.buildify.domain.auth.signup.service.SignupService;
import com.wareflow.buildify.dto.UserDTO;
import com.wareflow.buildify.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/components")
public class SignupController {

    @Autowired
    private SignupService signupService;

    @GetMapping("/pages-sign-up")
    public String signup() {
        return "components/pages-sign-up";
    }

    @PostMapping("/pages-sign-up")
    public String processSignup(@ModelAttribute UserDTO userDTO, RedirectAttributes rttr) {
        boolean result = signupService.register(userDTO);

        if (result) {
            rttr.addFlashAttribute("msg", "회원가입 성공!");
            return "redirect:/login";
        } else {
            rttr.addFlashAttribute("msg", "회원가입 실패");
            return "redirect:/signup";
        }
    }
}
