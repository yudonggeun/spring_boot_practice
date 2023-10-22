package com.example.spring_boot_example.chapter4.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        try {
            var reference = new ParameterizedTypeReference<Map<String, String>>() {
            };

            ResponseEntity<Map<String, String>> result = new RestTemplate().exchange("https://www.naver.com", HttpMethod.GET, null, reference);

            if (result.getStatusCode().is2xxSuccessful() && result.getBody() != null) {
                Map<String, String> body = result.getBody();
                return Health.up().withDetails(body).build();
            } else {
                return Health.down().withDetail("status", result.getStatusCode()).build();
            }
        } catch (RestClientException ex) {
            return Health.status("FATAL").withException(ex).build();
        }
    }
}
