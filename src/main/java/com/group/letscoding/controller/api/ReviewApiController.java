package com.group.letscoding.controller.api;

import com.group.letscoding.dto.review.ReviewWriteDto;
import com.group.letscoding.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewApiController {

    private ReviewService reviewService;

    @Autowired
    public ReviewApiController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/api/group/{id}/write")
    public ResponseEntity reviewWrite(
            @PathVariable("id") Integer id,
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ){
        ReviewWriteDto reviewWriteDto = new ReviewWriteDto();
        reviewWriteDto.setTitle(title);
        reviewWriteDto.setContent(content);
        reviewWriteDto.setId(id);

        ReviewWriteDto savedReview = reviewService.reviewWrite(reviewWriteDto);
        System.out.println(title);
        System.out.println(content);
        return ResponseEntity.status(HttpStatus.OK).body(savedReview);
    }

    /*@PostMapping("/write")
    public ResponseEntity reviewWrite(@RequestBody ReviewWriteDto reviewWriteDto){
        ReviewWriteDto reviewWriteDto1 = reviewService.reviewWrite(reviewWriteDto);
        return ResponseEntity.status(HttpStatus.OK).body(reviewWriteDto1);
    }*/

    /*@PutMapping("/edit")
    public String*/
}
