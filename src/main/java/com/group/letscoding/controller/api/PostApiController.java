package com.group.letscoding.controller.api;


import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.controller.AuthController;
import com.group.letscoding.controller.PostController;
import com.group.letscoding.domain.studypostcomment.StudyPostComment;
import com.group.letscoding.dto.CMRespDto;
import com.group.letscoding.dto.post.CommentSaveRequestDto;
import com.group.letscoding.dto.post.PostDto;
import com.group.letscoding.dto.post.PostUpdateDto;
import com.group.letscoding.dto.review.ReviewWriteDto;
import com.group.letscoding.handler.ex.CustomValidationApiException;
import com.group.letscoding.service.post.PostServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PostApiController {

    private final PostServiceImpl postService;
    private ModelAndView mav;

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public PostApiController(PostServiceImpl postService) {
        this.postService = postService;
    }


    @PostMapping("/api/studyPost/{studyPostId}/studyPostcomment")
    public ResponseEntity commentSave (@Valid @RequestBody CommentSaveRequestDto commentSaveRequestDto,
                                       BindingResult bindingResult,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails){

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
        }

        System.out.println("afdsdsfafdsfds" + principalDetails.getUser().getId());
        StudyPostComment comment = postService.writeComment(
                commentSaveRequestDto.getContent(),
                commentSaveRequestDto.getStudyPostId(),
                commentSaveRequestDto.getUserId(),
                principalDetails.getUser().getId());

        System.out.println("comment.getUserId : " + comment.getUser().getId());

        // postService.writeComment(commentSaveRequestDto);
        return new ResponseEntity<>(new CMRespDto<>(1, "댓글 작성 성공", comment), HttpStatus.CREATED);
    }
    @PostMapping("/post/edit/{recruitment_id}")
    public ResponseEntity<String> editReview(@PathVariable int recruitment_id,
                                             @RequestBody PostUpdateDto postUpdateDto) {
        System.out.println("@@@@@recruitment_id = " + recruitment_id);
        System.out.println("@2@@@postUpdateDto = " + postUpdateDto);


        try {
            postUpdateDto.setRecruitmentId(recruitment_id);

            Date startDate = convertStringToDate(postUpdateDto.getStart_date());
            Date endDate = convertStringToDate(postUpdateDto.getEnd_date());

//            postService.editPost(postDto, principalDetails.getUser());
            postService.editPost(postUpdateDto);
            return new ResponseEntity<>("수정이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("수정 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/api/study/create-post")  // 사용자 정보 받아옴.
//    public ResponseEntity<?> createPost(@RequestBody PostDto postDto,
//                                        @AuthenticationPrincipal PrincipalDetails principalDetails){
//
//        log.info(postDto.toString());
//
//        StudyPost studyPost = postService.savePost(postDto.getTitle(), postDto.getTopic(),
//                postDto.getStart_date(), postDto.getSkills(), postDto.getEnd_date(),postDto.getMax_num(), postDto.getContent(),
//                principalDetails.getUser().getId());
//
//        return new ResponseEntity<>(new CMRespDto<>(1, "글 저장 성공", studyPost), HttpStatus.CREATED);
//
//    }

    /**
     * 스터디 모집 글 상세 페이지 - kang
     * @param recruitmentId
     * @return
     */
    /*
    허찬 : 상세보기는 굳이 Restful하게 처리할 필요 없어서 통상적인 mvc 방식으로 처리
     */
//    // 2. 모집 글 상세보기 페이지
//    @GetMapping("api/study/study-recruitment/{recruitmentId}")
//    public ResponseEntity<?> detailPost(@PathVariable int recruitmentId){
//
//        System.out.println("PostApiController의  detailPost()_1 : " + recruitmentId);
//
//        PostResponseDto postResponseDto = postService.getPostById(recruitmentId);
//
//        System.out.println("PostApiController의  detailPost()_2 :" + postResponseDto.toString());
//
//        CMRespDto<PostResponseDto> result1 = new CMRespDto<>(1, "글 상세보기 성공", postResponseDto);
//        CMRespDto<PostResponseDto> result2 = new CMRespDto<>(1, "글 상세보기 실패", postResponseDto);
//        if (postResponseDto != null){
//            return new ResponseEntity<>(result1, HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(result2, HttpStatus.NOT_FOUND);
//        }
//    }

    public Date convertStringToDate(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            return formatter.parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}


