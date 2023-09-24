package com.group.letscoding.dto.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ReviewWriteDto {

    private Integer review_id;

    @Size(min = 1, max = 50)
    @NotBlank
    private String title;

    @Size(min = 1, max = 1200)
    @NotBlank
    private String content;

    private int id;

    public ReviewWriteDto(Integer review_id, String title, String content) {
        this.review_id = review_id;
        this.title = title;
        this.content = content;
    }
}
