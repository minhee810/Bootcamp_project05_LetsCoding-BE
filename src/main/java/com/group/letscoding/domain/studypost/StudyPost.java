package com.group.letscoding.domain.studypost;

import com.group.letscoding.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudyPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @Column(nullable = false)
    private String title;

    @Column
    private String topic;

    @Column(nullable = false)
    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user_id;

    @Column(nullable = false)
    private int max_num;

    @Column
    private Date start_date;

    @Column
    private Date end_date;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private String skills;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}
