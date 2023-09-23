package com.group.letscoding.controller.api;

import com.group.letscoding.domain.user.User;
import com.group.letscoding.dto.CMRespDto;
import com.group.letscoding.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("/api/auth/checkUsernameDuplicate")
    public ResponseEntity<?> checkUsernameDuplicate(@RequestBody String username) {
        boolean isDuplicate = authService.isUsernameDuplicate(username);
        return new ResponseEntity<>(new CMRespDto<>(1, "중복 검사 성공", isDuplicate), HttpStatus.OK);
    }

    @PostMapping("/api/auth/checkUserExist")
    public ResponseEntity<?> checkUserExist(@RequestBody String username) {
        User user = authService.getUser(username);

        if (user != null) {
            return new ResponseEntity<>(new CMRespDto<>(1, "사용자가 존재합니다.", user.getId()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CMRespDto<>(-1, "존재하지 않는 사용자입니다.", null), HttpStatus.OK);
        }
    }
}
