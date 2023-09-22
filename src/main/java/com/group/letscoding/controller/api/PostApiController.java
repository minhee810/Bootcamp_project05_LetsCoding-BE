package com.group.letscoding.controller.api;


import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.service.post.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.controller.AuthController;
import com.group.letscoding.dto.CMRespDto;
import com.group.letscoding.dto.post.PostDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostApiController {

    private final PostServiceImpl postService;
    private ModelAndView mav;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public PostApiController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @PostMapping("/api/study/create-post")  // 사용자 정보 받아옴.
    public ResponseEntity<?> createPost(@RequestBody PostDto postDto,
                                        @AuthenticationPrincipal PrincipalDetails principalDetails){

        log.info(postDto.toString());

        StudyPost studyPost = postService.savePost(postDto.getTitle(), postDto.getTopic(),
                postDto.getStart_date(), postDto.getSkills(), postDto.getEnd_date(),postDto.getMax_num(), postDto.getContent(),
                principalDetails.getUser().getUser_id());

        System.out.println("##########post######### : "+ studyPost);
        return new ResponseEntity<>(new CMRespDto<>(1, "글 저장 성공", studyPost), HttpStatus.CREATED);


    }

}


