package com.springboot.sampleproject.listener;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

// MQ 데이터 확인용 리스너
@Slf4j
@Component
public class MQListener {

    // MQ 데이터 비동기 식으로 받는 listener
    @Async
    @RabbitListener(queues = "sample")
    public void receiveMessage(final String message) throws InterruptedException {

        Thread.sleep(500);
        Gson gson = new Gson();

        Map<String, Object> map = gson.fromJson(message, HashMap.class);

        /** MQ 데이터가 아래 같은 구조일때
         * {
         *  "id":"joe",
         *  "currentPosition" : {
         *       "x": 100,
         *       "y": 200,
         *       "z": 300,
         *       "riskLevel" : 2,
         *       "poseName" : 3
         *   }
         * }
         */
        for(String key: map.keySet()) {
            log.info(key + " : " + map.get(key));
            if(key.equals("currentPosition")) {
                Map<String, Object> mPoseMap = (Map<String, Object>) map.get("currentPosition");
                for(String mKey : mPoseMap.keySet()) {
                    log.info(mKey + " : " + mPoseMap.get(mKey));
                }
            }
        }
    }
}
