package com.group.letscoding.domain.post;

import com.group.letscoding.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class StudyPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    @Column
    private String topic;

    private String content;

    @JoinColumn(name = "userId")
    @ManyToOne
    private User user;

    @Column
    private String skills;

    @Column(nullable = false)
    private int max_num;

    @Column
    private Date start_date;

    @Column
    private Date end_date;

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
