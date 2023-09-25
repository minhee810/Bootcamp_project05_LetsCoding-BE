package com.group.letscoding.controller;

import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.domain.review.Review;
import com.group.letscoding.domain.review.ReviewRepository;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.dto.review.ReviewWithUserName;
import com.group.letscoding.dto.review.ReviewWriteDto;
import com.group.letscoding.dto.review.ReviewWriteResponseDto;
import com.group.letscoding.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

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

    @GetMapping("/review")
    public String reviewForm(){
        return "group/review-form";
    }

    @GetMapping("/review/{id}/read/{review_id}")
    public String reviewRead(@PathVariable Integer id,
                             @PathVariable Integer review_id, Model model){

        Review reviewContent = reviewService.reviewRead(id, review_id);
        Review review = reviewRepository.findById(review_id).orElse(null);

        if(review == null) {
            throw new NullPointerException("해당 회고 글이 존재하지 않습니다.");
        }

        model.addAttribute("reviewContent", reviewContent);
        model.addAttribute("review", review);
        System.out.println(model.addAttribute("reviewContent", reviewContent));
        System.out.println(model.addAttribute("review", review));

        return "group/review-read";
    }


}
