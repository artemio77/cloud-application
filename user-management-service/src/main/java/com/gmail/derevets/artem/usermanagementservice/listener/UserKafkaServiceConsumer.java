package com.gmail.derevets.artem.usermanagementservice.listener;

import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class UserKafkaServiceConsumer {

    @Autowired
    private UserService userService;


    @KafkaListener(topics = "user-update", groupId = "group_id")
    public void consumeUserRegister(User user) throws IOException {
        log.info(String.format("#### -> Consumed message -> %s", user));
        UUID id = userService.saveUserInCassandra(user);
        log.info("Update USER ID {}", id);
    }
}
