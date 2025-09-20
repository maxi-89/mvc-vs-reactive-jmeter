package com.nerdlab.book_review_api_reactive.service;

import com.nerdlab.book_review_api_reactive.model.DTO.BookWithReviews;
import com.nerdlab.book_review_api_reactive.model.entity.Book;
import com.nerdlab.book_review_api_reactive.model.entity.Review;
import com.nerdlab.book_review_api_reactive.repository.BookRepository;
import com.nerdlab.book_review_api_reactive.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Flux<BookWithReviews> getBookWithReviewsWithProjection() {
        return bookRepository.findBooksWithReviewComments()
                .map(row -> {
                    Book book = new Book(row.getBookId(), row.getBookTitle(), row.getAuthor());
                    List<Review> reviews = Optional.ofNullable(row.getReviewComments())
                            .orElseGet(List::of)
                            .stream()
                            .map(Review::new)
                            .toList();
                    return new BookWithReviews(book, reviews);
                });
    }

    public Flux<BookWithReviews> getBookWithReviews() {
        return bookRepository.findAll()
                .flatMap(book ->
                        reviewRepository.findByBookId(book.getId()).collectList()
                                .map(reviews -> new BookWithReviews(book, reviews)), 100
                );
    }

    public Mono<Book> createBook(Book book) {
        return bookRepository.save(book);
    }

    public Mono<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .flatMap(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setAuthor(updatedBook.getAuthor());
                    return bookRepository.save(book);
                });
    }

    public Mono<Void> deleteBook(Long id) {
        return reviewRepository.findByBookId(id)
                .flatMap(reviewRepository::delete)
                .then(bookRepository.deleteById(id));
    }
}

