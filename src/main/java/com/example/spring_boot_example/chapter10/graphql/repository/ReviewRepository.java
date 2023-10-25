package com.example.spring_boot_example.chapter10.graphql.repository;

import com.example.spring_boot_example.chapter10.graphql.domain.Review;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ReviewRepository {

    private final List<Review> reviewList = new LinkedList<>();

    public ReviewRepository() {
        reviewList.add(new Review(1, 1, "홍길동", 1, "ㅋㅋㅋㅋㅋㅋ"));
        reviewList.add(new Review(2, 5, "김영국", 10, "ㅋㅋㅋㅋㅋㅋ"));
        reviewList.add(new Review(3, 4, "박상도", 21, "ㅋㅋㅋㅋㅋㅋ"));
        reviewList.add(new Review(4, 2, "홍민", 9, "ㅋㅋㅋㅋㅋㅋ"));
        reviewList.add(new Review(5, 2, "동만", 7, "ㅋㅋㅋㅋㅋㅋ"));
        reviewList.add(new Review(6, 3, "kkk", 1100, "ㅋㅋㅋㅋㅋㅋ"));
    }

    public Flux<Review> findByCourseId(Integer courseId) {
        return Flux.fromIterable(reviewList)
                .filter(review -> review.getCourseId().equals(courseId));
    }

    public Mono<Review> save(Review review) {
        review.setCourseId(1);
        return Mono.just(review);
    }
}