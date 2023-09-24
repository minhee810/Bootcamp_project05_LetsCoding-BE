package com.group.letscoding.dto.review;

import com.group.letscoding.domain.review.Review;

public class ReviewWithUserName {
    private Review review;
    private String userName;

    public ReviewWithUserName(Review review, String userName) {
        this.review = review;
        this.userName = userName;
    }

    public Review getReview() {
        return review;
    }

    public String getUserName() {
        return userName;
    }
}
