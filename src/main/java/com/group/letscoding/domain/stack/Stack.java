//package com.group.letscoding.domain.stack;
//
//import com.group.letscoding.domain.studygroup.StudyGroup;
//import com.group.letscoding.domain.user.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class Stack {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int stack_id;
//
//    @Column(nullable = false)
//    private String stack_name; //java, Spring
//
//    @ManyToOne
//    @JoinColumn(name = "user_id") // User 엔티티의 id를 참조
//    private User user_id;
//
//    @ManyToOne
//    @JoinColumn(name = "group_id") // StudyGroup 엔티티의 group_id를 참조
//    private StudyGroup group_id;
//
//    @ManyToOne
//    @JoinColumn(name = "post_id") // StudyPost 엔티티의 post_id를 참조
//    private StudyGroup post_id;
//
//    // 생성자, getter, setter 등 필요한 코드 작성
//}
