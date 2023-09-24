package com.group.letscoding.dto.post;

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
    private String skills;
    private String content;
    private Date start_date;
    private Date end_date;
    private Date create_date;
    private int max_num;

}
