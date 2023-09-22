package com.group.letscoding.dto.post;

import com.group.letscoding.domain.post.Post;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class PostDto {

    @NotBlank
    private String title;

    @NotBlank
    private String topic;

    @NotBlank
    private String content;

    private String skills;
    private Date start_date;
    private Date end_date;
    private int max_num;

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .topic(topic)
                .content(content)
                .max_num(max_num)
                .start_date(start_date)
                .end_date(end_date)
                .build();
    }

}


