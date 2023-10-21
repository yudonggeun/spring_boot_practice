package com.example.spring_boot_example.chapter4.listener;

import com.example.spring_boot_example.chapter4.exception.UrlNotAccessibleException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class UrlAccessibilityHandler {

    @Value("${api.url:https://www.navuder.com/}")
    private String url;

    @EventListener(classes = ContextRefreshedEvent.class)
    public void listen(){
        // 외부 API URL에 접근 할 수 없는 상태를 만들기 위해
        // 의도적으로 UrlNotAccessibleException 예외를 던지도록 설정
        if(url.contains("naver")) return;
        throw new UrlNotAccessibleException(url);
    }
}
