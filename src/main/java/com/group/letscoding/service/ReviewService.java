package com.group.letscoding.service;

import com.group.letscoding.domain.review.Review;
import com.group.letscoding.domain.review.ReviewRepository;
import com.group.letscoding.domain.user.User;
import com.group.letscoding.dto.review.ReviewWriteDto;
import com.group.letscoding.dto.review.ReviewWriteResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private User user;
    private ReviewWriteDto reviewWriteDto;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewWriteDto reviewWrite(ReviewWriteDto reviewWriteDto) throws NullPointerException {
        try {
            Review review = new Review();

            review.setTitle(reviewWriteDto.getTitle());
            review.setContent(reviewWriteDto.getContent());
            review.setCreatedDate(LocalDateTime.now());

            Review savedReview = reviewRepository.save(review);

            ReviewWriteDto savedReviewDto = new ReviewWriteDto();  // ReviewWriteDto 객체를 생성
            savedReviewDto.setTitle(savedReview.getTitle());      // savedReview에서 정보를 가져와서 ReviewWriteDto에 설정
            savedReviewDto.setContent(savedReview.getContent());  // savedReview에서 정보를 가져와서 ReviewWriteDto에 설정

            return savedReviewDto;
        } catch (NullPointerException e) {
            e.printStackTrace(); // 스택 트레이스 출력
            throw e; // 예외 다시 던지기
        }
    }



    public Page<ReviewWriteResponseDto> getReviewByGroupId(Integer id, Pageable pageable) {
        // groupId 를 통해 해당 그룹의 목록을 가져옴
        Page<Review> reviews = reviewRepository.reviewList(id, pageable);

        List<ReviewWriteResponseDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviews.getContent()) {
            String userName = user.getUsername();
            String title = review.getTitle();
            String content = review.getContent();
            ReviewWriteResponseDto reviewDto = new ReviewWriteResponseDto(userName, title, content);
            reviewDtoList.add(reviewDto);
        }

        return new PageImpl<ReviewWriteResponseDto>(reviewDtoList, pageable, reviews.getTotalElements());
    }

    public Long getTotalReviewCountByGroupId(Integer id) {
        return reviewRepository.countReviewByGroupId(id);
    }
}
