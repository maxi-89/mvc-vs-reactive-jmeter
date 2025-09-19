package com.nerdlab.book_review_api_reactive.repository;

import com.nerdlab.book_review_api_reactive.model.entity.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {
}
