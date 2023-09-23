//package com.group.letscoding.domain.chat;
//
//import com.group.letscoding.domain.user.User;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@Getter
//@Setter
//public class Message {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private String content;
//    private LocalDateTime timestamp;
//
//    @ManyToOne
//    private ChatRoom chatRoom;
//    @ManyToOne
//    private User sender;
//
//    // 게터, 세터 및 생성자
//}
