package com.example.spring_boot_example.chapter8.resocket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Course {

    private final UUID courseId = UUID.randomUUID();
    private final long created = Instant.now().getEpochSecond();
    private String courseName;

    public Course(String courseName) {
        this.courseName = courseName;
    }
}
