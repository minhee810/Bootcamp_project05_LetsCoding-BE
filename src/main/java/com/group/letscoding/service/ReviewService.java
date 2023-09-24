package com.group.letscoding.service;

import com.group.letscoding.config.auth.PrincipalDetails;
import com.group.letscoding.domain.group.Group;
import com.group.letscoding.domain.group.GroupRepository;
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
    private GroupRepository groupRepository;
    private User user;
    private ReviewWriteDto reviewWriteDto;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, GroupRepository groupRepository) {
        this.reviewRepository = reviewRepository;
        this.groupRepository = groupRepository;
    }

    public Page<ReviewWriteResponseDto> getReviewByGroupId(Integer id, Pageable pageable) {
        // groupId 를 통해 해당 그룹의 목록을 가져옴
        Page<Review> reviews = reviewRepository.reviewList(id, pageable);

        List<ReviewWriteResponseDto> reviewDtoList = new ArrayList<>();
        for (Review review : reviews.getContent()) {
            String title = review.getTitle();
            String content = review.getContent();
            int review_id = review.getReview_id();
            ReviewWriteResponseDto reviewDto = new ReviewWriteResponseDto(review_id, title, content);
            reviewDtoList.add(reviewDto);
        }

        return new PageImpl<ReviewWriteResponseDto>(reviewDtoList, pageable, reviews.getTotalElements());
    }

    public Long getTotalReviewCountByGroupId(Integer id) {
        return reviewRepository.countReviewByGroupId(id);
    }

    public Review reviewRead(Integer id, Integer review_id) {
        Review review = reviewRepository.findById(review_id).orElse(null);

        if(review == null) {
            throw new NullPointerException("해당 회고 글이 존재하지 않습니다.");
        }

        return review;
    }

    public ReviewWriteDto editReview(ReviewWriteDto reviewWriteDto) throws Exception {
        try {
            Integer reviewId = reviewWriteDto.getReview_id();
            Review review = reviewRepository.findById(reviewId).orElse(null);

            if (review == null) {
                throw new NullPointerException("해당 리뷰가 존재하지 않습니다.");
            }

            // 리뷰의 제목과 내용을 업데이트합니다.
            review.setTitle(reviewWriteDto.getTitle());
            review.setContent(reviewWriteDto.getContent());

            // 리뷰 저장
            reviewRepository.save(review);

            // 수정된 리뷰 정보 반환
            return reviewWriteDto;
        } catch (Exception e) {
            e.printStackTrace(); // 스택 트레이스 출력
            throw new Exception("리뷰 수정 중 오류가 발생했습니다.");
        }
    }

    public void deleteReview(Integer id, Integer review_id) throws Exception {
        try {
            reviewRepository.deleteById(review_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("리뷰 삭제 중 오류가 발생했습니다.");
        }
    }

    public void saveReview(ReviewWriteDto reviewWriteDto) throws Exception {

        try {
            Integer groupId = reviewWriteDto.getId();

            // 해당 groupId로 그룹 정보를 가져옴
            Group group = groupRepository.findById(groupId).orElse(null);

            if (group == null) {
                throw new NullPointerException("해당 그룹이 존재하지 않습니다.");
            }

            // Review 엔터티 생성
            Review review = new Review();
            review.setTitle(reviewWriteDto.getTitle());
            review.setContent(reviewWriteDto.getContent());
            review.setGroup(group);

            // 현재 시간을 설정
            review.setCreatedDate(LocalDateTime.now());

            // 리뷰 저장
            reviewRepository.save(review);
        } catch (Exception e) {
            e.printStackTrace(); // 스택 트레이스 출력
            throw new Exception("리뷰 작성 중 오류가 발생했습니다.");
        }
    }

}
