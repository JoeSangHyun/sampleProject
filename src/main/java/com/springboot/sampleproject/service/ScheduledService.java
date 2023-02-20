package com.springboot.sampleproject.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@NoArgsConstructor
public class ScheduledService {

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void fixedDelayJob() {
        log.info("This is Delayed");
    }

//    @Async
//    @Scheduled(initialDelay = 1000, fixedRate = 1000)
//    public void fixedRateJob() throws InterruptedException{
////        log.info("This job is executed per a second");
//        log.info("fixedRateJob started");
//        Thread.sleep(2500);
//        log.info("fixedRateJob end");
//    }
}
