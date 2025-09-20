package com.nerdlab.book_review_api_reactive.webflux_functional.handler;


import com.nerdlab.book_review_api_reactive.model.DTO.BookWithReviews;
import com.nerdlab.book_review_api_reactive.model.entity.Book;
import com.nerdlab.book_review_api_reactive.service.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BookHandler {

    private final BookServiceImpl bookService;

    public Mono<ServerResponse> getAllBooks(ServerRequest request) {
        return ServerResponse.ok().body(bookService.getBookWithReviewsWithProjection(), BookWithReviews.class);
    }



    public Mono<ServerResponse> createBook(ServerRequest request) {
        return request.bodyToMono(Book.class)
                .flatMap(bookService::createBook)
                .flatMap(book -> ServerResponse.ok().bodyValue(book));
    }

    public Mono<ServerResponse> updateBook(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(Book.class)
                .flatMap(book -> bookService.updateBook(id, book))
                .flatMap(updatedBook -> ServerResponse.ok().bodyValue(updatedBook));
    }

    public Mono<ServerResponse> deleteBook(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return bookService.deleteBook(id)
                .then(ServerResponse.noContent().build());
    }
}

