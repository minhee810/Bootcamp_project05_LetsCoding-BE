package com.group.letscoding.controller;

import com.group.letscoding.domain.user.User;
import com.group.letscoding.dto.auth.SignupDto;
import com.group.letscoding.handler.ex.CustomValidationException;
import com.group.letscoding.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            // log.info(signupDto.toString());
            User user = signupDto.toEntity();
            User userEntity = authService.signup(user);
            // log.info(userEntity.toString());
            return "user/login-form";
        }
    }
}
