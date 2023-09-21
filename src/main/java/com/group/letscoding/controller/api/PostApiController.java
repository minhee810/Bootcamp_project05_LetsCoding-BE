package com.group.letscoding.controller.api;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostDTO;
import com.group.letscoding.service.post.PostServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostApiController {

    private PostServiceImpl postService;
    private ModelAndView mav;

    public PostApiController(PostServiceImpl postService) {
        this.postService = postService;
    }
    @GetMapping(value = "/post/search/{keyword}")
    public String StudyRecruitSearch(@RequestParam("keyword") String keyword, Model model){

        //1. 검색어를 사용하여 게시글 검색
        List<StudyPost> searchResults = postService.findByTitleContaining(keyword);

        //2.Model 객체에 리스트 담기
        // 리다이렉션 시 데이터를 추가합니다.
        model.addAttribute("searchResults", searchResults);

        return "redirect:/post/list";
    }


}
