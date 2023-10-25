package com.example.spring_boot_example.chapter10.graphql.repository;

import com.example.spring_boot_example.chapter10.graphql.domain.Course;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;


@Repository
public class CourseRepository {

    private final Map<String, Course> storage = new HashMap<>();

    public CourseRepository() {
        storage.put("c1", new Course(1, "course1", "c1", "test"));
        storage.put("c2", new Course(2, "course2", "c2", "test"));
        storage.put("c3", new Course(3, "course3", "c3", "test"));
        storage.put("c4", new Course(4, "course4", "c4", "test"));
        storage.put("c5", new Course(5, "course5", "c5", "test"));
    }

    public Flux<Course> findByCategory(String category) {
        return Flux
                .fromIterable(storage.entrySet())
                .filter(entry -> entry.getKey().equals(category))
                .map(Map.Entry::getValue);

    }

    public Flux<Course> findAll() {
        return Flux
                .fromIterable(storage.values());
    }

    public Mono<Course> save(Course course) {
        storage.put(course.getCategory(), course);
        return Mono.just(course);
    }
}