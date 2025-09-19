package com.nerdlab.book_review_api_reactive.repository;

import com.nerdlab.book_review_api_reactive.model.entity.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Long> {
    Flux<Review> findByBookId(Long id);
}
