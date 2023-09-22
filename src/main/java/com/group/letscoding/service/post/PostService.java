package com.group.letscoding.service.post;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostDto;
import com.group.letscoding.dto.post.PostResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
interface PostService {

    //게시판 게시물 가져오기
    List<com.group.letscoding.domain.studypost.StudyPost> getRecruitBoardList();

    public Page<com.group.letscoding.domain.studypost.StudyPost> getRecruitBoardPage(int page, int size);

    //검색
    public Page<com.group.letscoding.domain.studypost.StudyPost> findByTitleContaining(String keyword, Pageable pageable);
    public Page<com.group.letscoding.domain.studypost.StudyPost> findBySkillContaining(String keyword, Pageable pageable);

    StudyPost savePost(String title, String topic, Date startDate, String skills, Date endDate, int max_num, String content, Long userId);
    PostResponseDto updatePost(int id, PostDto postDto);
    PostResponseDto deletePost(int id) throws Exception;


}
