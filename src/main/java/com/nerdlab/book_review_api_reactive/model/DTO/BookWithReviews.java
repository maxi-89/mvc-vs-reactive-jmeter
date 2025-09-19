package com.nerdlab.book_review_api_reactive.model.DTO;

import com.nerdlab.book_review_api_reactive.model.entity.Book;
import com.nerdlab.book_review_api_reactive.model.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BookWithReviews {
    private Book book;
    private List<Review> reviews;
}
