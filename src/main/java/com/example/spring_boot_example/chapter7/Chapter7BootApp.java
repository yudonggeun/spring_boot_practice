package com.example.spring_boot_example.chapter7;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Chapter7BootApp {

    public static void main(String[] args) {
        SpringApplication.run(Chapter7BootApp.class);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("title")
                        .version("0.0.1")
                        .description("description")
                        .termsOfService("http://swagger.io/terms/") //서비스 약관 정보 주소
                        .license(new License()
                                .name("MIT")
                                .url("http://springdoc.org") // 라이센스 관련 주소
                        )
                );
    }
}
