package com.nerdlab.book_review_api_reactive.repository;

import com.nerdlab.book_review_api_reactive.model.DTO.BookWithReviewProjection;
import com.nerdlab.book_review_api_reactive.model.entity.Book;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

    @Query("""
        SELECT
                   b.id AS book_id,
                   b.title AS book_title,
                   b.author AS book_author,
                   string_agg(r.comment, '|') AS review_comments
               FROM
                   book b
               LEFT JOIN
                   review r ON b.id = r.book_id
               GROUP BY
                   b.id, b.title, b.author
               ORDER BY
                   b.id
    """)
    Flux<BookWithReviewProjection> findBooksWithReviewComments();
}
