package com.example.spring_boot_example.chapter2.part2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
// yml, yaml 파일을 지정해서 사용할 수 없다. 추가 작업이 필요함
@PropertySource("classpath:dbConfig.properties")
public class DbConfiguration {

    @Autowired
    private Environment environment;

    @Override
    public String toString() {
        return "Username : " + environment.getProperty("user") +
               ",  Password : " + environment.getProperty("password");
    }
}
