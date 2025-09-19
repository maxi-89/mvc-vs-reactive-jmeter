package com.nerdlab.book_review_api_reactive.webflux_functional.config;

import com.nerdlab.book_review_api_reactive.webflux_functional.handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> bookRoutes(BookHandler handler) {
        return route(GET("/api/v2/books"), handler::getAllBooks)
                .andRoute(POST("/api/v2/books"), handler::createBook)
                .andRoute(PUT("/api/v2/books/{id}"), handler::updateBook)
                .andRoute(DELETE("/api/v2/books/{id}"), handler::deleteBook);
    }
}
