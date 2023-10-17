package com.example.spring_boot_example.chapter2.part5;

import jakarta.validation.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseTrackerApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(CourseTrackerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CourseTrackerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        var course = new Course();
        course.setId(1);
        course.setRating(0);
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var violations = validator.validate(course);
        violations.forEach(violation -> logger.error("A constraint violation has occurred. Violation detail: [{}]", violation));
    }

    @Bean
    public CommandLineRunner password() {
        return args -> {
            var validator = Validation.buildDefaultValidatorFactory().getValidator();

            var user1 = new User("sbip01", "sbip");
            var violations = validator.validate(user1);
            logger.error("password for user 1 do not adhere to the password policy");
            violations.forEach(violation -> logger.error("Violation details [{}]", violation.getMessage()));

            var user2 = new User("sbip02", "Sbip01$4UDfg");
            violations = validator.validate(user2);
            logger.error("password for user 2 do not adhere to the password policy");
            violations.forEach(violation -> logger.error("Violation details [{}]", violation.getMessage()));

            var user3 = new User("sbip03", "Sbip01$4UDfgggg");
            violations = validator.validate(user3);
            logger.error("password for user 3 do not adhere to the password policy");
            violations.forEach(violation -> logger.error("Violation details [{}]", violation.getMessage()));

            var user4 = new User("sbip04", "Sbip014UDfgggg");
            violations = validator.validate(user4);
            logger.error("password for user 4 do not adhere to the password policy");
            violations.forEach(violation -> logger.error("Violation details [{}]", violation.getMessage()));
        };
    }
}
