package com.group.letscoding.domain.studypost;

import com.group.letscoding.dto.post.PostInterface;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class StudyPost  implements PostInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int post_id;

    @Column(nullable = false)
    private String title;

    @Column
    private String topic;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String user_id;

    @Column
    private Date start_date;

    @Column
    private Date end_date;

    @Column(nullable = false)
    private Date createDate;

    @Column(nullable = false)
    private String skill;
}
