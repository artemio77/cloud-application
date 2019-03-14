package com.gmail.derevets.artem.usermanagementservice.kafka.service.counsumer;

import com.gmail.derevets.artem.usermanagementservice.kafka.service.KafkaManager;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
@EnableKafka
public class UserKafkaServiceConsumer {

    @Autowired
    private UserService userService;

    @KafkaListener(topics = KafkaManager.USER_CREATE_TOPIC,
            containerFactory = "userKafkaListenerContainerFactory",
            groupId= "group_id")
    public void consumeUserRegister(User user) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", user));
        UUID id = userService.saveUserInCassandra(user);
        log.info("Update USER ID {}", id);
    }
}
