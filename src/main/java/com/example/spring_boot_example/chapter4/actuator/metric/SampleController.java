package com.example.spring_boot_example.chapter4.actuator.metric;

import com.example.spring_boot_example.chapter2.part5.Course;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class SampleController {

    @Autowired
    private Counter counter;
    @Autowired
    private Timer timer;
    @Autowired
    private DistributionSummary distributionSummary;
    @GetMapping("/sample")
    public Course createCourse() throws Exception {

        counter.increment();
        Course course = Course.builder()
                .id(100)
                .description("test")
                .name("hello")
                .category("category")
                .rating(1)
                .build();

        distributionSummary.record(course.getRating());
        return timer.recordCallable(() -> {
            Thread.sleep(1000);
            return course;
        });
    }
}
