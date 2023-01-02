package com.springboot.sampleproject.controller;

import com.springboot.sampleproject.model.dto.Worker;
import com.springboot.sampleproject.service.RabbitClientService;
import com.springboot.sampleproject.service.dRoolsEngineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MQClientController {

    private final RabbitClientService rabbitClientService;
    private final dRoolsEngineService droolsEngineService;

    @GetMapping("mq")
    public String setMQDataController(
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
        return rabbitClientService.RabbitMQService(worker);
    }
}
