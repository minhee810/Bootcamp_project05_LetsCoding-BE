package com.group.letscoding.service;

import com.group.letscoding.domain.user.User;
import com.group.letscoding.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ChangeInfoService {

    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public ChangeInfoService(UserRepository userRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void changePassword(String username, String newPassword, String confirmPassword) throws NoSuchElementException {
        User user = this.userRepository.findByUsername(username);

        if (user != null) {
            // 현재 비밀번호가 올바를 경우
            String encNewPassword = encoder.encode(newPassword);
            user.setPassword(encNewPassword);
            userRepository.save(user);
        } else {
            throw new NoSuchElementException("사용자를 찾을 수 없거나 현재 비밀번호와 일치하지 않습니다.");
        }
    }

    public void changeInfo(String username, String name) throws NoSuchElementException {
        User user = this.userRepository.findByUsername(username);

        if (user != null) {
            user.setName(name);
            userRepository.save(user);
        } else {
            throw new NoSuchElementException("입력하신 정보가 적절하지 않습니다.");
        }
    }
}
