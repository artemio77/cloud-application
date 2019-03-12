package com.gmail.derevets.artem.usermanagementservice.service.serviceImpl;

import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.repository.UserRepository;
import com.gmail.derevets.artem.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserById(UUID id) {
        return null;
    }

    @Override
    public UUID findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUser(User user) {
        return null;
    }

    @Override
    public UUID saveUserInCassandra(User user){
        log.info("Save user {}", user);
        User createdUser = userRepository.insert(user);
        log.info("User ID {}", createdUser.getId());
        return createdUser.getId();
    }
}
