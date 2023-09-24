package com.group.letscoding.controller;

import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.domain.user.UserRepository;
import com.group.letscoding.handler.ex.CustomValidationException;
import com.group.letscoding.service.ChangeInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MypageController {

    private static final Logger log = LoggerFactory.getLogger(MypageController.class);
    private final UserRepository userRepository;

    @Autowired
    private ChangeInfoService changeInfoService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public MypageController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/mypage/mypageForm")
    public String mypage(Model model, Principal principal) {
        try {
            if (principal != null) {
                PrincipalDetails principalDetails = (PrincipalDetails) ((Authentication) principal).getPrincipal();
                User user = principalDetails.getUser();
                model.addAttribute("user", user);
                return "mypage/mypage";
            } else {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("error", "사용자 정보를 찾을 수 없습니다.");
                throw new CustomValidationException("정보 불러오기 실패", errorMap);
            }
        } catch (Exception e) {
            log.error("Error in mypage method:", e);
            return "에러발생";
        }
    }

    @GetMapping("/mypage/changePassword")
    public String changePasswordForm(Model model, Principal principal) {

        if (principal != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) ((Authentication) principal).getPrincipal();
            User user = principalDetails.getUser();
            model.addAttribute("user", user);
            return "mypage/changePassword";
        } else {
            throw new NullPointerException();
        }
    }

    @GetMapping("/mypage/changeInfo")
    public String changeInfo(Model model, Principal principal) {

        if (principal != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) ((Authentication) principal).getPrincipal();
            User user = principalDetails.getUser();
            model.addAttribute("user", user);
            return "mypage/change-info";
        } else {
            throw new NullPointerException();
        }
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam String newPassword, @RequestParam String confirmPassword,
                                 @ModelAttribute("user") User user, BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("error", "비밀번호를 확인해주세요.");
                throw new CustomValidationException("비밀번호 변경 실패", errorMap);
            }

            if (principal != null) {
                PrincipalDetails principalDetails = (PrincipalDetails) ((Authentication) principal).getPrincipal();
                user = principalDetails.getUser();

                // ChangeInfoService를 사용하여 비밀번호 변경
                changeInfoService.changePassword(user.getUsername(), newPassword, confirmPassword);
            }
            return "redirect:/mypage/mypageForm";
        } catch (Exception e) {
            log.error("Error in changePassword method :", e);
            return "에러발생";
        }
    }

    @PostMapping("/changeInfo")
    public String changeInfo(@RequestParam String name, @ModelAttribute("user") User user,
                             BindingResult bindingResult, Principal principal, RedirectAttributes redirectAttributes){
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errorMap = new HashMap<>();
                errorMap.put("error", "입력 정보가 적절하지 않습니다.");
                throw new CustomValidationException("정보 수정 실패", errorMap);
            }
            if (principal != null) {
                PrincipalDetails principalDetails = (PrincipalDetails) ((Authentication) principal).getPrincipal();
                user = principalDetails.getUser();

                changeInfoService.changeInfo(user.getUsername(), name);
            }
            return "redirect:/mypage/mypageForm";
        } catch (Exception e) {
            log.error("Error in changeInfo method : ", e);
            return "에러발생";
        }
    }

}