package com.nerdlab.book_review_api_reactive.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("book")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    private Long id;

    private String title;

    private String author;
}
