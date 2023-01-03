package com.springboot.sampleproject.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

// MQ 데이터 확인용 리스너
@Slf4j
@Component
public class MQListener {

    @RabbitListener(queues = "sample")
    public void receiveMessage(final Message message) {
//  message 내용 확인하려면 Message -> String으로 변경
        log.info(message.toString());
    }
}
