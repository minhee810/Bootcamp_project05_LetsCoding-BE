package com.group.letscoding.dto.post;

import com.group.letscoding.domain.studypost.StudyPost;
import lombok.Data;

import java.util.Date;
@Data
public class PostInsertDto {

    private String title ;
    private String topic;
    private String skills;
    private String content;
    private int max_num;
    private Date start_date;
    private Date end_date;
    private Long user_id;


    public StudyPost toEntity() {
        return StudyPost.builder()
                .title(title)
                .topic(topic)
                .content(content)
                .max_num(max_num)
                .skills(skills)
                .start_date(start_date)
                .end_date(end_date)
                .build();
    }
}
