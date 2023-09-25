package com.group.letscoding.dto.post;

import com.group.letscoding.domain.studypost.StudyPost;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
public class PostUpdateDto {

    @NotBlank
    private int recruitmentId;

    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String topic;

    @NotBlank
    private String content;
    private String skills;
    private String user_id;
    private String start_date;
    private String end_date;
    private int max_num;

}
