package com.example.spring_boot_example.chapter10.graphql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Review {

    private Integer id;
    private Integer courseId;
    private String reviewerName;
    private Integer rating;
    private String comment;
}