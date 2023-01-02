package com.springboot.sampleproject.controller;

import com.springboot.sampleproject.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.sampleproject.model.dto.Product;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final TestService testService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/test")
    public List<Product> test() {
        return testService.getAllDataList();
    }

    @GetMapping("/testOne")
    public Product getOneData(String idx) {

        return testService.getOneData(idx);
    }
}
