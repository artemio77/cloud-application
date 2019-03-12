package com.gmail.derevets.artem.usermanagementservice.controller;

import com.gmail.derevets.artem.usermanagementservice.clients.UserClient;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class UserFeingController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserService userService;

    @PutMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        log.info("POST user {}", user);
        return userClient.createUser(user);
    }

    @PostMapping("/activate/{code}")
    @ResponseStatus(HttpStatus.CREATED)
    public JSONObject activateUser(@PathVariable Long code) {
        log.info("POST activate user");
        User user = userClient.activateUser(code);
        log.info("Activated user {}", user);
        userService.saveUserInCassandra(user);
        return new JSONObject().put("id", user.getId()).put("email", user.getEmail());
    }


}
