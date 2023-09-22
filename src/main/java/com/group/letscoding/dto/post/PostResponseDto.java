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

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String topic;

    @NotBlank
    private String content;
    private Date start_date;
    private Date end_date;
    private Date create_date;
    private int max_num;

    public PostResponseDto(StudyPost studyPost) {
        this.id = studyPost.getId();
        this.title = studyPost.getTitle();
        this.topic = studyPost.getTopic();
        this.content = studyPost.getContent();
        this.start_date = studyPost.getStart_date();
        this.end_date= studyPost.getEnd_date();
        this.max_num = studyPost.getMax_num();
    }

}
