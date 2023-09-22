package com.group.letscoding.dto.post;

import com.group.letscoding.domain.post.Post;
import com.group.letscoding.domain.user.User;
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

    @NotBlank
    private String topic;

    @NotBlank
    private String content;
    private Date start_date;
    private Date end_date;
    private Date create_date;
    private int max_num;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.topic = post.getTopic();
        this.content = post.getContent();
        this.start_date = post.getStart_date();
        this.end_date= post.getEnd_date();
        this.max_num = post.getMax_num();
    }

}
