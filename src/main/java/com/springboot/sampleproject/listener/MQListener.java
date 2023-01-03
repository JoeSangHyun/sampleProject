package com.springboot.sampleproject.listener;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

// MQ 데이터 확인용 리스너
@Slf4j
@Component
public class MQListener {

    @RabbitListener(queues = "sample")
    public void receiveMessage(final String message) {

        Gson gson = new Gson();

        Map<String, Object> map = gson.fromJson(message, Map.class);

        // Map 출력
        for(Map.Entry<String, Object> entry: map.entrySet()) {
            log.info("[ MQ DATA ] : " + entry.getKey() + "=" + entry.getValue());
        }

//        log.info(jsonStr);
    }
}
