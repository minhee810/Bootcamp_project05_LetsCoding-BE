//package com.group.letscoding.domain.review;
//
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//import org.hibernate.annotations.CreationTimestamp;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Entity
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer review_id;
//
//    @Column(nullable = false)
//    private String title;
//
//    @Column(nullable = false)
//    private Integer group_id;
//
//    @Column(nullable = false)
//    private Integer user_id;
//
//    @CreationTimestamp
//    private LocalDateTime createdDate;
//}
