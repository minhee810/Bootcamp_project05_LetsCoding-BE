package com.group.letscoding.controller.api;

import com.group.letscoding.dto.review.ReviewWriteDto;
import com.group.letscoding.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/review")
public class ReviewApiController {

    private ReviewService reviewService;

    @Autowired
    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/write")
    public ResponseEntity reviewWrite(@RequestBody ReviewWriteDto reviewWriteDto){
        ReviewWriteDto reviewWriteDto1 = reviewService.reviewWrite(reviewWriteDto);
        return ResponseEntity.status(HttpStatus.OK).body(reviewWriteDto1);
    }

    /*@PutMapping("/edit")
    public String*/
}
