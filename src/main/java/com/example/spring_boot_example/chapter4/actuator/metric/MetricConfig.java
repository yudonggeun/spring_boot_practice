package com.example.spring_boot_example.chapter4.actuator.metric;

import io.micrometer.core.instrument.*;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricConfig {

    /*
    MeterRegistry 는 메트릭 데이터를 수집하고 레포팅하는 역할을 담당합니다. 레포팅은 여러 백엔드 시스템에 가능하며, 프로메테우스 등 다양한 시스템을 지원합니다.
     */
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config()
                .commonTags("application", "course-tracker");
    }

    @Bean
    public Counter createCourseCounter(MeterRegistry registry) {
        return Counter.builder("api.courses.created.count")
                .description("Total number of courses created")
                .register(registry);
    }

    @Bean
    public Gauge createCourseGauge(MeterRegistry registry){
        return Gauge.builder("api.courses.created.gauge", () -> {
                    return 10;
                }).description("Total courses created")
                .register(registry);

    }

    @Bean
    public Timer createCoursesTimer(MeterRegistry registry){
        return Timer.builder("api.courses.creation.time")
                .description("Course creation time")
                .register(registry);
    }

    @Bean
    public DistributionSummary createDistributionSummary(MeterRegistry registry){
        return DistributionSummary.builder("api.courses.rating.distribution.summary")
                .description("Rating distribution summary")
                .register(registry);
    }
}
