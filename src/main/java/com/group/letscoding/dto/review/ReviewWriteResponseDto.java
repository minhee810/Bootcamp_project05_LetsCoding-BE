package com.group.letscoding.dto.review;


public class ReviewWriteResponseDto {
    private String userName;
    private String title;
    private String content;

    public ReviewWriteResponseDto(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}
