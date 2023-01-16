package com.springboot.sampleproject.controller;

import com.springboot.sampleproject.config.LargeDataRowHandler;
import com.springboot.sampleproject.service.TestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.sampleproject.model.dto.Product;
import org.springframework.web.servlet.function.ServerRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Mybatis 사용 컨트롤러
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


    @GetMapping("/select")
    public void getSelect(@RequestParam Map map, HttpServletRequest request, HttpServletResponse response){
        try {
//            Map map = new HashMap<>();
//            map.put("idx",10);

            response.setHeader("Accept","*/*");
//            response.setContentType("application/json;charset=UTF-8");
            response.setContentType("text/plain;charset=UTF-8");

            LargeDataRowHandler largeDataRowHandler =new LargeDataRowHandler(response.getWriter());
            testService.select(map,largeDataRowHandler);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
