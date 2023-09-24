package com.group.letscoding.dto.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentSaveRequestDto {
    private Long userId;
    private int studyPostId;
    private String content;
}
