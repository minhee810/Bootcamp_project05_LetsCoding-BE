package com.group.letscoding.service;

import com.group.letscoding.domain.review.Review;
import com.group.letscoding.domain.review.ReviewRepository;
import com.group.letscoding.dto.review.ReviewWithUserName;
import com.group.letscoding.dto.review.ReviewWriteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    private ReviewWriteDto reviewWriteDto;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewWriteDto reviewWrite(ReviewWriteDto reviewWriteDto) throws NullPointerException {
        Review review = new Review();

        review.setTitle(reviewWriteDto.getTitle());
        review.setContent(reviewWriteDto.getContent());

        review.setCreatedDate(LocalDateTime.now());

        Review savedReview = reviewRepository.save(review);

        ReviewWriteDto savedReviewDto = new ReviewWriteDto();
        savedReviewDto.setTitle(savedReviewDto.getTitle());
        savedReviewDto.setContent(savedReviewDto.getContent());

        return savedReviewDto;
    }

    /*public Page<ReviewWithUserName> getReviewsWithUserNameByGroupId(Integer groupId, Pageable pageable) {
        // groupId 를 통해 해당 그룹의 목록을 가져옴
        StudyGroup group = getGroupById(groupId);
        Page<Review> reviews = reviewRepository.findByStudyGroup(group, pageable);

        return reviews.map(review -> {
            String userName = review.getUser().getName();
            return new ReviewWithUserName(review, userName);
        });
    }*/

}
