package com.springboot.sampleproject.listener;

import com.google.gson.Gson;
import com.springboot.sampleproject.model.dto.Worker;
import com.springboot.sampleproject.service.dRoolsEngineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.*;

// MQ 데이터 확인용 리스너
@Slf4j
@RequiredArgsConstructor
@Component
public class MQListener {

    private final dRoolsEngineService droolsEngineService;
    private static final Worker worker = new Worker();

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
            if(key.equals("id")) {
                worker.setId(map.get(key).toString());
            }
            if(key.equals("currentPosition")) {
                Map<String, Object> mPoseMap = (Map<String, Object>) map.get("currentPosition");

                worker.setX((Double)mPoseMap.get("x"));
                log.info("[ x ] " +  " : " + mPoseMap.get("x"));

                worker.setY((Double)mPoseMap.get("y"));
                log.info("[ y ] " +  " : " + mPoseMap.get("y"));

                worker.setZ((Double)mPoseMap.get("z"));
                log.info("[ z ] " +  " : " + mPoseMap.get("z"));

            }
        }

       String riskReuslt = droolsEngineService.drools(worker.getX(),worker.getY(),worker.getPoseName());
       worker.setRiskLevel(riskReuslt);
       log.info("[ worker Info ]" + worker.toString());

    }

    @Async
    @RabbitListener(queues = "pose")
    public void receiveMessagePose(final String message) throws InterruptedException,NumberFormatException,ClassCastException {

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
        int poseResult = Integer.parseInt(map.get("pose").toString());
        worker.setPoseName(poseResult);
        log.info("poseResult " + poseResult);
    }
}
