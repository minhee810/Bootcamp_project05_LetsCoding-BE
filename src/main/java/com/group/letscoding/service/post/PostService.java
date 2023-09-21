package com.group.letscoding.service.post;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostDTO;
import com.group.letscoding.dto.post.PostInterface;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
interface PostService {

    //게시판 게시물 가져오기
    List<StudyPost> getRecruitBoardList();

    public Page<StudyPost> getRecruitBoardPage(int page, int size);

    //검색
    List<StudyPost> findByTitleContaining(String keyword);


}
