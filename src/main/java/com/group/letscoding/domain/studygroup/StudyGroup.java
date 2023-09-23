package com.group.letscoding.domain.studygroup;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StudyGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int group_id;

    @Column(nullable = false)
    private String group_name;

    @Column(nullable = false)
    private String topic; //개발 분야(프론트엔드,백엔드)

    @Column(nullable = false)
    private String category; //스터디 그룹 종류(프로젝트,스터디)

    @Column
    private String description;

    @Column(nullable = false)
    private String start_date;

    @Column(nullable = false)
    private String end_date;

    @Column(nullable = false)
    private int maxNum;

    @Column(nullable = false)
    private Date created_by;

    @Column(nullable = false)
    private Date createDate;

    @Column(nullable = false)
    private String stack;
}
