package com.group.letscoding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller                     // * 페이지 이동 컨트롤러
public class PostController {

    @GetMapping({"/", "/post/list"})
    public String postList() {
        return "post/post-list";
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

}
