package com.group.letscoding.controller.api;

import com.group.letscoding.dto.review.ReviewWriteDto;
import com.group.letscoding.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReviewApiController {

    private ReviewService reviewService;

    @Autowired
    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/api/group/{id}/write")
    public ResponseEntity<?> reviewWrite(
            @PathVariable("id") Integer id,
            @RequestBody ReviewWriteDto reviewWriteDto
    ){
        try {
            // 성공적으로 처리된 경우
            reviewService.saveReview(reviewWriteDto);
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            // 오류가 발생한 경우
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
        }
    }

    @PostMapping("/review/{id}/edit/{review_id}")
    public ResponseEntity<String> editReview(@PathVariable Integer id,
                                             @PathVariable Integer review_id,
                                             @RequestBody ReviewWriteDto reviewWriteDto) {
        try {
            reviewWriteDto.setId(id);
            reviewWriteDto.setReview_id(review_id);

            ReviewWriteDto editedReview = reviewService.editReview(reviewWriteDto);
            return new ResponseEntity<>("수정이 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("수정 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/review/{id}/delete/{review_id}")
    public ResponseEntity<String> deleteReview(@PathVariable Integer id,
                                               @PathVariable Integer review_id) {
        try {
            reviewService.deleteReview(id, review_id);
            return new ResponseEntity<>("삭제가 완료되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("삭제 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
