package com.example.spring_boot_example.chapter2.part3;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

// 요구사항 : 스프링 부트 애플리케이션 시작 시 CommandLineRunner를 사용해서 애플리케이션 초기화 코드를 실행해야 한다.
@SpringBootApplication
public class CourseTrackerApplication implements CommandLineRunner {

    protected final Log logger = LogFactory.getLog(getClass());
    public static void main(String[] args) {
        SpringApplication.run(CourseTrackerApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("CourseTrackerApplication CommandLineRunner has executed");
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> logger.info("bean injection command line");
    }
}
