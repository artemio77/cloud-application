package com.gmail.derevets.artem.authservice.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.derevets.artem.authservice.exception.UserNotFoundException;
import com.gmail.derevets.artem.authservice.model.User;
import com.gmail.derevets.artem.authservice.repository.UserRepository;
import com.gmail.derevets.artem.authservice.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @HystrixCommand(fallbackMethod = "registerNewAccountAndGetData_Fallback")
    public void registerNewAccount(final User user) {
        User newUser = User.getBuilder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(passwordEncoder.encode(user.getPassword()))
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonLocked(true)
                .isEnabled(false)
                .build();
        newUser.setVerificationCode(generateUniqueVerificationCode());
        userRepository.save(user);

    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User Not Found " + email));
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }


    public User activateUser(Long code) {
        Optional<User> userOptional = userRepository.findByEmail(userRepository.checkUniqueVerificationCode(code));
        userOptional.ifPresent(it -> {
            throw new IllegalArgumentException("Code Invalid: ");
        });
        User user = userOptional.get();
        if (!user.getIsEnabled()) {
            user.setIsEnabled(true);
            user.setVerificationCode(null);
            userRepository.save(user);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.convertValue(user, JsonNode.class);
            rabbitTemplate.setExchange("UserTopic");
            rabbitTemplate.convertAndSend("user-save", node.toString());
        }
        return user;
    }

    public String checkUserVerificationCode(Long code) {
        return userRepository.checkUniqueVerificationCode(code);
    }

    private Long generateUniqueVerificationCode() {
        for (; ; ) {
            Random r = new Random();
            Long code = r.longs(1, 10000, 99999).findFirst().getAsLong();
            log.info(code.toString());
            if (userRepository.checkUniqueVerificationCode(new Random()
                    .longs(1, 10000, 99999).findAny().getAsLong()) == null) {
                return code;
            }
        }
    }


}
