package com.group.letscoding.controller.api;

import com.group.letscoding.domain.studypost.StudyPost;
import com.group.letscoding.dto.post.PostDTO;
import com.group.letscoding.service.post.PostServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
