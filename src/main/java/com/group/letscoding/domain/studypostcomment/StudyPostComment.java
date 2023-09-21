package com.group.letscoding.domain.studypostcomment;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StudyPostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int post_id;

    @Column(nullable = false)
    private int user_id;

    @Column(nullable = false)
    private Date createDate;
}
