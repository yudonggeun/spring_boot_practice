package com.example.spring_boot_example.chapter8.resocket;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.rsocket.server.LocalRSocketServerPort;
import org.springframework.messaging.rsocket.RSocketRequester;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseControllerTest {

    private static RSocketRequester requester;

    @BeforeAll
    public static void setUpOnce(
            @Autowired RSocketRequester.Builder builder,
            @LocalRSocketServerPort Integer port) {

        requester = builder.tcp("localhost", port);
    }

    @DisplayName("request - response test")
    @Test
    public void testRequestResponse() {

        Mono<Course> courseMono = requester.route("request-response")
                .data(new Course("Spring"))
                .retrieveMono(Course.class);

        StepVerifier.create(courseMono)
                .consumeNextWith(course -> assertThat(course.getCourseName())
                        .isEqualTo("Your course name: Spring")
                )
                .verifyComplete();
    }

    @DisplayName("fire and forget test")
    @Test
    void testFireAndForget() {
        var courseMono = requester.route("fire-and-forget")
                .data(new Course("Spring"))
                .retrieveMono(Void.class);

        StepVerifier.create(courseMono)
                .verifyComplete();
    }

    @DisplayName("request stream test")
    @Test
    void testRequestStream() {
        var courseFlux = requester.route("request-stream")
                .data(new Course("Spring"))
                .retrieveFlux(Course.class);

        StepVerifier.create(courseFlux)
                .consumeNextWith(course ->
                        assertThat(course.getCourseName()).isEqualTo("Your course name: Spring. Response#0")
                )
                .expectNextCount(0)
                .consumeNextWith(course ->
                        assertThat(course.getCourseName()).isEqualTo("Your course name: Spring. Response#1")
                )
                .thenCancel()
                .verify();
    }

    @DisplayName("channel test")
    @Test
    void testChannel() {
        var setting1 = Mono.just(Integer.valueOf(2)).delayElement(Duration.ofMillis(0));
        var setting2 = Mono.just(Integer.valueOf(1)).delayElement(Duration.ofMillis(2500));

        var settings = Flux.concat(setting1, setting2);

        var stream = requester.route("stream-stream")
                .data(settings)
                .retrieveFlux(Course.class);

        StepVerifier
                .create(stream)
                .consumeNextWith(course ->
                        assertThat(course.getCourseName()).isEqualTo("Spring. Response #0")
                )
                .consumeNextWith(course ->
                        assertThat(course.getCourseName()).isEqualTo("Spring. Response #0")
                )
                .consumeNextWith(course ->
                        assertThat(course.getCourseName()).isEqualTo("Spring. Response #1")
                )
                .consumeNextWith(course ->
                        assertThat(course.getCourseName()).isEqualTo("Spring. Response #2")
                )
                .thenCancel()
                .verify();
    }
}