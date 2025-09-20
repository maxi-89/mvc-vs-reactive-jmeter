package com.nerdlab.book_review_api_reactive.model.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BookWithReviewProjection {
    private Long bookId;
    private String bookTitle;
    private String author;
    private List<String> reviewComments;
}

