package com.example.spring_boot_example.chapter10.graphql.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Course {

    private Integer id;
    private String name;
    private String category;
    private String description;
}