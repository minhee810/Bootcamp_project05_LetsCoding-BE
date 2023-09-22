package com.group.letscoding.controller.api;


import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostInsertDto;
import com.group.letscoding.dto.post.PostResponseDto;
import com.group.letscoding.service.post.PostServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.controller.AuthController;
import com.group.letscoding.dto.CMRespDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostApiController {

    private final PostServiceImpl postService;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public PostApiController(PostServiceImpl postService) {
        this.postService = postService;
    }

    /**
     * 스터디 모집 글 작성 insert - kang
     * @param postInsertDto
     * @param principalDetails
     * @return
     */
    @PostMapping("/api/study/create-post")  // 사용자 정보 받아옴.
    public ResponseEntity<?> createPost(@RequestBody PostInsertDto postInsertDto,
                                        @AuthenticationPrincipal PrincipalDetails principalDetails){

        log.info(postInsertDto.toString());

        StudyPost studyPost = postService.savePost(postInsertDto, principalDetails.getUser().getId());

        System.out.println(" PostApiController의 createPost() : "+ studyPost);
        return new ResponseEntity<>(new CMRespDto<>(1, "글 저장 성공", studyPost), HttpStatus.CREATED);
    }

    /**
     * 스터디 모집 글 상세 페이지 - kang
     * @param recruitmentId
     * @return
     */
    // 2. 모집 글 상세보기 페이지
    @GetMapping("/study/study-recruitment/{recruitmentId}")
    public ResponseEntity<?> detailPost(@PathVariable Long recruitmentId){

        System.out.println("PostApiController의  detailPost()_1 : " + recruitmentId);

        PostResponseDto postResponseDto = postService.getPostById(recruitmentId);

        System.out.println("PostApiController의  detailPost()_2 :" + postResponseDto.toString());

        CMRespDto<PostResponseDto> result1 = new CMRespDto<>(1, "글 상세보기 성공", postResponseDto);
        CMRespDto<PostResponseDto> result2 = new CMRespDto<>(1, "글 상세보기 실패", postResponseDto);
        if (postResponseDto != null){
            return new ResponseEntity<>(result1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(result2, HttpStatus.NOT_FOUND);
        }


    }




}


