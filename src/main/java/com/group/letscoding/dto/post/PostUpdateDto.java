package com.group.letscoding.dto.post;

import com.group.letscoding.domain.post.StudyPost;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Data
public class PostUpdateDto {

    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String topic;

    @NotBlank
    private String content;
    private String user_id;
    private Date start_date;
    private Date end_date;

    public StudyPost toEntity() {
        return StudyPost.builder()
                .id(id)
                .title(title)
                .topic(topic)
                .content(content)
                .start_date(start_date)
                .end_date(end_date)
                .build();
    }

}
