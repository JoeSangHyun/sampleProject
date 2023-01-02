package com.springboot.sampleproject.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MQListener {

    @RabbitListener(queues = "sample")
    public void receiveMessage(final Message message) {
//  public void receiveMessage(final String message) {
        log.info(message.toString());
    }
}
