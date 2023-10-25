package com.example.spring_boot_example.chapter7;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "my first swagger controller", description = "This is REST controller")
public class SwaggerController {

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "test api 1")
    public Object test1() {
        return "test1";
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @Operation(summary = "id test api")
    public Object test2(@PathParam("id") String id){
        return id;
    }
}
