package com.gmail.derevets.artem.authservice.controller;


import com.gmail.derevets.artem.authservice.model.User;
import com.gmail.derevets.artem.authservice.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping({"/authManagement"})
@RestController
public class UserController {

    private final UserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenStore tokenStore;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder, TokenStore tokenStore, RabbitTemplate rabbitTemplate) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenStore = tokenStore;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PutMapping("/register")
    public @ResponseBody
    User registerUser(@RequestBody User user) {
        log.info(user.toString());
        userService.registerNewAccount(user);
        log.info("user {}", user);
        return new user;
    }

    @GetMapping(path = "/{code}")
    public ResponseEntity<Long> checkCodeByEmail(@PathVariable("code") Long code) {
        log.info(code.toString());
        User user = userService.activateUser(code);
        return new ResponseEntity<>(user.getVerificationCode(), HttpStatus.OK);
    }

    @GetMapping("/exist/{email:.+}")
    public Boolean existUserByEmail(@PathVariable String email) {
        if (userService.findUserByEmail(email) != null) {
            log.info("true");
            return true;
        }
        log.info("false");
        return false;
    }

    @GetMapping(value = "/user/{email:.+}")
    public ResponseEntity<User> user(@PathVariable("email") String email) {
        User user = userService.findUserByEmail(email);
        log.info("User,{}", user.toString());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "/getToken/{email:.+}")
    public ResponseEntity<String> getUserToken(@PathVariable("email") String email) {
        Map<String, String> userTokenStore = new HashMap<>();
        String token = tokenStore.findTokensByClientIdAndUserName("fooClientIdPassword", email)
                .stream()
                .map(OAuth2AccessToken::getValue).collect(Collectors.toList()).get(0);
        log.info(token);
        return new ResponseEntity<String>(token, HttpStatus.OK);
    }

}
