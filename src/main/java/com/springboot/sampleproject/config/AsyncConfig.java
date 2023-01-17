package com.springboot.sampleproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

// 비동기 처리 config
@EnableAsync
@Configuration
public class AsyncConfig {
// 비동기 Config
    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setQueueCapacity(5000000);
        executor.setThreadNamePrefix("JSH-ASYNC-");
        executor.initialize();
        return executor;
    }
}
