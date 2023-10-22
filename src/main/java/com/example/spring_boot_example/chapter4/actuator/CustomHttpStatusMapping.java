package com.example.spring_boot_example.chapter4.actuator;

import org.springframework.boot.actuate.health.HttpCodeStatusMapper;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

@Component
public class CustomHttpStatusMapping implements HttpCodeStatusMapper {
    @Override
    public int getStatusCode(Status status) {
        if(status.equals(Status.UP)) return 400;
        return 200;
    }
}
