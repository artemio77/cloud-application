package com.gmail.derevets.artem.authservice.client;

import com.gmail.derevets.artem.authservice.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFallback implements ClientUserController {


    @Override
    public void createUser(User user) {
        log.info("User {} not can't be save", user);
    }

    @Override
    public User getUser(String email) {
        log.info("User {} not found", email);
        return new User();
    }
}
