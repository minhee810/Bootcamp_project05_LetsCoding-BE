package com.group.letscoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "user/join-form";
    }
}
