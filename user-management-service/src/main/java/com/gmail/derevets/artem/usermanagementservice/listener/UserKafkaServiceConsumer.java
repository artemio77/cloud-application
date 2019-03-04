package com.gmail.derevets.artem.usermanagementservice.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class UserKafkaServiceConsumer {

    @KafkaListener(topics = "users-register", groupId = "group_id")
    public void consumeUserRegister(String message) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", message));
    }
}
