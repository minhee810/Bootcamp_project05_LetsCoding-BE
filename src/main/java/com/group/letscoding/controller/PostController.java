package com.group.letscoding.controller;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostDTO;
import com.group.letscoding.service.post.PostServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostServiceImpl postService;

    private static final ModelAndView mav = new ModelAndView();

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);


    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    //0.게시판에 글 나열(페이지 이동에 따른 이동 없음)
    @GetMapping("/post/list")
    public ModelAndView listRecruit(@RequestParam(name = "page", defaultValue = "0") int page,
                                    @RequestParam(name = "size", defaultValue = "5") int size){

        //1. postService의 메소드를 통해
        // 게시글 목록 받아오기
        List<StudyPost> recruitBoardList = postService.getRecruitBoardList();
        List<PostDTO> postDTOList = new ArrayList<>();

        Page<StudyPost> recruitBoardPage = postService.getRecruitBoardPage(page, size);

        mav.addObject("recruitBoardList", recruitBoardPage.getContent());
        mav.addObject("currentPage", page);
        mav.addObject("totalPages", recruitBoardPage.getTotalPages());

        mav.setViewName("post/post-list");
        return mav;
    }


    // 1. 모집 글 작성 페이지로 이동
    @GetMapping("/study/create-post")
    public String createPostView () {
        return "post/create-post";
    }

    // 2. 모집 글 상세보기 페이지로 이동
    @GetMapping("/study/study-recruitment/{recruitmentId}")
    public String recruitment (){
        return "post/study-recruitment";
    }

    // 3. 모집 글 수정 화면으로 이동 -> updatePost.html
    @GetMapping("/study/update-post")
    public String updatePost () {
        return "update-post";
    }

    // 4. 스터디 그룹 생성 화면 -> createGroup.html
    @GetMapping("/study/create-group")
    public String createGroup () {
        return "member/create-group";
    }

    private List<PostDTO> getPostDTOS(List<PostDTO> postDTOList, List<StudyPost> postList) {
        for(StudyPost post : postList){
            PostDTO postDTO = new PostDTO();
            postDTO.setPost_id(post.getPost_id());
            postDTO.setTitle(post.getTitle());
            postDTO.setSkill(post.getSkill());

            postDTOList.add(postDTO);
        }

        return postDTOList;
    }
}
