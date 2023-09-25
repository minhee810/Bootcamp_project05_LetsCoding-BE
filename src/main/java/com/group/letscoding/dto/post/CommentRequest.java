package com.group.letscoding.dto.post;

import lombok.Data;

@Data
public class CommentRequest {

    private int studyPostId;
    private String content;
}
