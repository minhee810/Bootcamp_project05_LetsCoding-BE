package com.group.letscoding.dto.review;

import lombok.Data;

@Data
public class ReviewWriteResponseDto {
    private int review_id;
    private String title;
    private String content;

    public ReviewWriteResponseDto(int review_id, String title, String content) {
        this.review_id = review_id;
        this.title = title;
        this.content = content;
    }
}
