//package com.group.letscoding.domain.chat;
//
//import com.group.letscoding.domain.user.User;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Getter
//@Setter
//public class ChatRoom {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String name;
//    // 다른 필드 (예: 생성일 등)
//
//    @ManyToMany
//    private Set<User> users = new HashSet<>();
//
//    // 게터, 세터 및 생성자
//}
