package com.group.letscoding.service.post;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.domain.studypost.StudyPostRepository;
import com.group.letscoding.dto.post.PostDTO;
import com.group.letscoding.dto.post.PostInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final StudyPostRepository studyPostRepository;

    public PostServiceImpl(StudyPostRepository studyPostRepository) {
        this.studyPostRepository = studyPostRepository;
    }

    @Override
    public List<StudyPost> getRecruitBoardList() {
        List<StudyPost> postList;

        postList = studyPostRepository.findAllBy();

        return postList;

//        List<PostDTO> postList = new ArrayList<>();
//
//        //1. StudyPost.title 뽑아오기
//        List<String> titles = studyPostRepository.findAllTitles();
//
//        for (String title : titles) {
//            PostDTO postDTO = new PostDTO();
//            postDTO.setTitle(title);
//
//            //2.title 기반으로 post_id 뽑기
//            int post_id = studyPostRepository.findStackIdByStudyPostTitle(title);
//
//            //3. post_id 기반으로 stack 가져오기
//            List<String> stacknames = studyPostRepository.findByPostId(post_id);
//            postDTO.setStack(stacknames);
//
//            postList.add(postDTO);
//        }
//        return postList;

    }

    //0-1.페이징
    @Override
    public Page<StudyPost> getRecruitBoardPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studyPostRepository.findAll(pageable);
    }


    @Override
    public List<StudyPost> findByTitleContaining(String keyword) {

        List<StudyPost> postList;
        postList = studyPostRepository.findByTitleContaining(keyword);

        return postList;
    }
}