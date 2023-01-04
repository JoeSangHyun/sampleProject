package com.springboot.sampleproject.controller;

import com.springboot.sampleproject.service.dRoolsEngineService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// dRoolsEngine -> MQ 리스너에서 받는걸로 구성
@Slf4j
@RestController
@RequiredArgsConstructor
public class dRoolsEngine {

    private final dRoolsEngineService droolsEngineService;

    // dRoolsEngine 비동기 테스트 용
    @GetMapping("drools")
    public String drools() {
        log.info("Test Async");

        for(int i=0;i<50;i++) {
            droolsEngineService.asyncMethod(i);
        }
        log.info("Test Async end");
//        return "";

        return droolsEngineService.drools(10241.093046189342,734.6519734283407,2);
    }

    @GetMapping("droolsTest")
    public String droolsTest() {
        return droolsEngineService.drools(10241.093046189342,734.6519734283407,2);
    }
}
