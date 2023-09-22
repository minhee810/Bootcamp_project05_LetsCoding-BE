package com.group.letscoding.controller.api;

import com.group.letscoding.dto.CMRespDto;
import com.group.letscoding.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/api/auth/checkUsernameDuplicate")
    public ResponseEntity<?> checkUsernameDuplicate(@RequestBody String username) {
        boolean isDuplicate = authService.isUsernameDuplicate(username);
        return new ResponseEntity<>(new CMRespDto<>(1, "중복 검사 성공", isDuplicate), HttpStatus.OK);
    }
}