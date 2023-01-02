package com.springboot.sampleproject.service;

import com.springboot.sampleproject.model.dto.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitClientService {

    private final RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE_NAME = "Sample";
    private static final String ROUTING_KEY = "sample.routingkey.#";


    public String RabbitMQService(Worker worker) {
        if(worker != null){
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, worker.toString());
        }
        else{
            return "Message not forwarded.";
        }
        return "Message Sending OK";
    }

}
