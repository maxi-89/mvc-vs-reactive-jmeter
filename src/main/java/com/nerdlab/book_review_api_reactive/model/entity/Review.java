package com.nerdlab.book_review_api_reactive.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    private Long id;

    private String comment;

    private int rating;

    @Column("book_id")
    private Long bookId;
}
