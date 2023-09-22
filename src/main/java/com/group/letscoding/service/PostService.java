package com.group.letscoding.service;

import com.group.letscoding.domain.post.Post;
import com.group.letscoding.dto.post.PostDto;
import com.group.letscoding.dto.post.PostResponseDto;

import java.util.Date;

public interface PostService {
    Post savaPost(String title, String topic, Date startDate, String skills, Date endDate, int max_num, String content, Long userId);
    PostResponseDto updatePost(int id, PostDto postDto);
    PostResponseDto deletePost(int id) throws Exception;


   }
