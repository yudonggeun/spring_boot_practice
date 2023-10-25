package com.example.spring_boot_example.chapter10.graphql.controller;

import com.example.spring_boot_example.chapter10.graphql.domain.Course;
import com.example.spring_boot_example.chapter10.graphql.domain.Review;
import com.example.spring_boot_example.chapter10.graphql.repository.CourseRepository;
import com.example.spring_boot_example.chapter10.graphql.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GraphqlCourseController {

    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    @QueryMapping
    Flux<Course> courses(){
        log.info("course method");
        return this.courseRepository.findAll();
    }

    @QueryMapping
    Flux<Review> reviews(@Argument Integer courseId){
        log.info("reviews by id method");
        return this.reviewRepository.findByCourseId(courseId);
    }

    @QueryMapping
    Flux<Course> coursesByCategory(@Argument String category){
        log.info("coursesByCategory method");
        return this.courseRepository.findByCategory(category);
    }

    @SchemaMapping(typeName = "Course")
    Flux<Review> reviews(Course course){
        log.info("reviews by course method");
        return this.reviewRepository.findByCourseId(course.getId());
    }

    @MutationMapping
    Mono<Course> addCourse(@Argument String name, @Argument String category, @Argument String description){
        log.info("addCourse method");
        return this.courseRepository.save(new Course(null, name, category, description));
    }

    @MutationMapping
    Mono<Review> addReview(@Argument Integer courseId, @Argument String reviewName, @Argument Integer rating, @Argument String comment){
        log.info("addReview method");
        return this.reviewRepository.save(new Review(null, courseId, reviewName, rating, comment));
    }
}
