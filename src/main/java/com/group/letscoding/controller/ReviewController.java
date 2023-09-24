package com.group.letscoding.controller;

import com.group.letscoding.domain.review.ReviewRepository;
import com.group.letscoding.dto.review.ReviewWriteResponseDto;
import com.group.letscoding.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/review/{id}/write")
    public String reviewForm(@PathVariable Integer id, Model model){
        model.addAttribute("id", id);
        return "group/review-form";
    }

    @GetMapping("/review/{id}/list")
    public String reviewList(@PathVariable Integer id, Model model,
                             @PageableDefault(size = 10) Pageable pageable){

        Page<ReviewWriteResponseDto> reviews = reviewService.getReviewByGroupId(id, pageable);
        model.addAttribute("reviews", reviews);

        // 해당 그룹의 전체 리뷰 수를 가져 옴 (페이징)
        Long totalItems = reviewService.getTotalReviewCountByGroupId(id);
        model.addAttribute("totalItems", totalItems);

        return "group/review-list";
    }
}
