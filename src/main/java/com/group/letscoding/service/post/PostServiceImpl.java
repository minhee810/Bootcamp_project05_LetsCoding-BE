package com.group.letscoding.service.post;


import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.domain.studypost.StudyPostRepository;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.domain.user.UserRepository;
import com.group.letscoding.dto.post.PostDto;
import com.group.letscoding.dto.post.PostInsertDto;
import com.group.letscoding.dto.post.PostResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private final StudyPostRepository studyPostRepository;

    @Autowired
    private final UserRepository userRepository;

    public PostServiceImpl(StudyPostRepository studyPostRepository, UserRepository userRepository) {
        this.studyPostRepository = studyPostRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<StudyPost> getRecruitBoardList() {
        List<com.group.letscoding.domain.studypost.StudyPost> studyPostList;

        studyPostList = studyPostRepository.findAllBy();

        return studyPostList;
    }

    //0-1.페이징
    @Override
    public Page<StudyPost> getRecruitBoardPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studyPostRepository.findAll(pageable);
    }

    @Override
    public Page<StudyPost> findByTitleContaining(String keyword, Pageable pageable) {
        logger.info("START");
        Page<com.group.letscoding.domain.studypost.StudyPost> postList;
       postList = studyPostRepository.findByTitleContaining(keyword,pageable);
        logger.info("END");
        return postList;
    }

    @Override
    public Page<StudyPost> findBySkillContaining(String keyword, Pageable pageable) {

        Page<com.group.letscoding.domain.studypost.StudyPost> postList;
        postList = studyPostRepository.findBySkillContaining(keyword,pageable);

        return postList;
    }

    @Override
    public StudyPost savePost(PostInsertDto postInsertDto, Long userId){
        User userEntity = userRepository.findById(userId).orElseThrow(()->{
            return new RuntimeException("유저 아이디를 찾을 수 없습니다.");
        });

        StudyPost studyPost = new StudyPost();
        studyPost.setTitle(postInsertDto.getTitle());
        studyPost.setTopic(postInsertDto.getTopic());
        studyPost.setSkills(postInsertDto.getSkills());
        studyPost.setContent(postInsertDto.getContent());
        studyPost.setStart_date(postInsertDto.getStart_date());
        studyPost.setEnd_date(postInsertDto.getEnd_date());
        studyPost.setMax_num(postInsertDto.getMax_num());
        studyPost.setUser_id(userEntity);

        System.out.println("savePost() : " + studyPost);
        return studyPostRepository.save(studyPost);
    }

    @Override
    public PostResponseDto getPostById(Long recruitmentId) {
        System.out.println("#### PostServiceImpl getPostById() #### : " + recruitmentId);

        StudyPost postEntity = studyPostRepository.findById(recruitmentId).orElseThrow(()->{
            return new EntityNotFoundException("해당 글이 존재하지 않습니다.  recruitmentId : "+ recruitmentId);
        });

        // 게시글 Entity를 DTO로 변환
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setId(postEntity.getId());
        postResponseDto.setTitle(postEntity.getTitle());
        postResponseDto.setTopic(postEntity.getTopic());
        postResponseDto.setContent(postEntity.getContent());
        postResponseDto.setStart_date(postEntity.getStart_date());
        postResponseDto.setEnd_date(postEntity.getEnd_date());
        postResponseDto.setMax_num(postEntity.getMax_num());

        System.out.println("#### service PostResponseDto #### :" + postResponseDto.toString());
        return postResponseDto;
    }

    @Override
    public PostResponseDto updatePost(int id, PostDto postDto) {
        return null;
    }

    @Override
    public PostResponseDto deletePost(int id) throws Exception {
        return null;
    }
}
