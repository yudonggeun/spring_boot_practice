package com.example.spring_boot_example.chapter2.part2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import java.util.Properties;

@SpringBootApplication
//@EnableConfigurationProperties(AppProperties.class)
@ConfigurationPropertiesScan
public class SpringBootAppDemoApplication {

    private static final Logger log = LoggerFactory.getLogger(SpringBootAppDemoApplication.class);
    public static void main(String[] args) {
        var properties = new Properties();
        // 해당 설정은 spring.config.import에 명시한 파일이 존재하지 않는 경우 전략을 소개 설정한 것이다.
        properties.setProperty("spring.config.on-not-found", "ignore");

        var springApp = new SpringApplication(SpringBootAppDemoApplication.class);

        springApp.setDefaultProperties(properties);
        var applicationContext = springApp.run(args);

        var dbConfiguration = applicationContext.getBean(DbConfiguration.class);
        log.info(dbConfiguration.toString());

        var appService = applicationContext.getBean(AppService.class);
        log.info(appService.getAppProperties().toString());
    }
}
