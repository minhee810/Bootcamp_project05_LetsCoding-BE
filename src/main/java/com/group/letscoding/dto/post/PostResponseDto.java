package com.group.letscoding.dto.post;

import com.group.letscoding.domain.studypost.StudyPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PostResponseDto {

    private int id;

    @NotBlank
    private String title;

    private String studyPostComments;

    @NotBlank
    private String topic;

    @NotBlank
    private String skills;
    private String content;
    private Date start_date;
    private Date end_date;
    private Date create_date;
    private int max_num;

    public PostResponseDto(StudyPost studyPost) {
        this.id = studyPost.getPost_id();
        this.title = studyPost.getTitle();
        this.topic = studyPost.getTopic();
        this.content = studyPost.getContent();
        this.start_date = studyPost.getStart_date();
        this.skills = studyPost.getSkills();
        this.end_date= studyPost.getEnd_date();
        this.max_num = studyPost.getMax_num();
    }


}
