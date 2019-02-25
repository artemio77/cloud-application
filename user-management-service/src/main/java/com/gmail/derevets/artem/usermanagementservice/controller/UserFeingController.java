package com.gmail.derevets.artem.usermanagementservice.controller;

import com.gmail.derevets.artem.usermanagementservice.clients.UserClient;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserFeingController {

    @Autowired
    private UserClient userClient;

    @PostMapping("/create")
    public void createUser(@RequestBody User user) {
        log.info("POST user {}", user);
        userClient.createUser(user);
    }
}
