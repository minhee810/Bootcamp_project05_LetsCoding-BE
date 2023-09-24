package com.group.letscoding.service.post;


import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.domain.studypost.StudyPostRepository;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.domain.user.UserRepository;
import com.group.letscoding.dto.post.PostDto;
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

    @Autowired
    private final StudyPostRepository studyPostRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Autowired
    private final UserRepository userRepository;

    public PostServiceImpl(StudyPostRepository studyPostRepository, UserRepository userRepository) {
        this.studyPostRepository = studyPostRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<StudyPost> getRecruitBoardList() {
        List<StudyPost> studyPostList;

        studyPostList = studyPostRepository.findAllBy();

        return studyPostList;

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
    public Page<StudyPost> findByTitleContaining(String keyword, Pageable pageable) {
        logger.info("START");
        Page<StudyPost> postList;
       postList = studyPostRepository.findByTitleContaining(keyword,pageable);
        logger.info("END");
        return postList;
    }

    @Override
    public Page<StudyPost> findBySkillContaining(String keyword, Pageable pageable) {

        Page<StudyPost> postList;
        postList = studyPostRepository.findBySkillContaining(keyword,pageable);

        return postList;
    }


    @Override
    public StudyPost savePost(String title, String topic, Date startDate,
                              String skills, Date endDate,
                              int max_num, String content,
                              Long userId){

        User userEntity = userRepository.findById(userId).orElseThrow(()->{
            return new RuntimeException("유저 아이디를 찾을 수 없습니다.");
        });

        StudyPost studyPost = new StudyPost();
        studyPost.setTitle(title);
        studyPost.setTopic(topic);
        studyPost.setSkills(skills);
        studyPost.setContent(content);
        studyPost.setStart_date(startDate);
        studyPost.setEnd_date(endDate);
        studyPost.setMax_num(max_num);
        studyPost.setUser_id(userEntity);

        System.out.println("savePost() : " + studyPost);
        return studyPostRepository.save(studyPost);
    }

    @Override
    public PostResponseDto getPostById(int recruitmentId) {
        System.out.println("#### PostServiceImpl getPostById() #### : " + recruitmentId);


        StudyPost postEntity = studyPostRepository.findById(recruitmentId).orElseThrow(()->{
            return new EntityNotFoundException("해당 글이 존재하지 않습니다.  recruitmentId : "+ recruitmentId);
        });

        // 게시글 Entity를 DTO로 변환
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setId(postEntity.getPost_id());
        postResponseDto.setSkills(postEntity.getSkills());
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
