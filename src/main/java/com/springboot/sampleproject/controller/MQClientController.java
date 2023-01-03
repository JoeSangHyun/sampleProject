package com.springboot.sampleproject.controller;

import com.springboot.sampleproject.model.dto.Worker;
import com.springboot.sampleproject.service.dRoolsEngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MQClientController {

    private static final String EXCHANGE_NAME = "Sample";
    private static final String ROUTING_KEY = "sample.routingkey.#";
    private final RabbitTemplate rabbitTemplate;
    private final dRoolsEngineService droolsEngineService;

    // MQ 데이터 삽입
    @PostMapping(value = "mqRiskLevel")
    public String sendMQDataController(
            @RequestParam String id,
            @RequestParam double x,
            @RequestParam double y,
            @RequestParam double z,
            @RequestParam int poseName
    ) {

        Worker worker = new Worker();
        worker.setId(id);
        worker.setX(x);
        worker.setY(y);
        worker.setZ(z);
        worker.setRiskLevel(droolsEngineService.drools(x,y,poseName));

        rabbitTemplate.convertAndSend(EXCHANGE_NAME,ROUTING_KEY,worker.toString());

        return "message sending!";

    }
}
