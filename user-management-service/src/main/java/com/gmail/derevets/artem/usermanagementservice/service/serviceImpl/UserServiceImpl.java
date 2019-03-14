package com.gmail.derevets.artem.usermanagementservice.service.serviceImpl;

import com.gmail.derevets.artem.usermanagementservice.exception.user.UserException;
import com.gmail.derevets.artem.usermanagementservice.exception.user.UserNotFoundException;
import com.gmail.derevets.artem.usermanagementservice.model.Role;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.repository.UserRepository;
import com.gmail.derevets.artem.usermanagementservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findUserById(UUID id) throws UserException {
        log.info("UUID {}", id);
        User user = userRepository.findByUserKey_Id(id);
        log.info("GET USER {}", user);
        Optional.ofNullable(user).orElseThrow(() -> new UserNotFoundException("User " + id + " Not Found"));
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUserKey_Email(email);
    }

    @Override
    public User findUser(User user) {
        return null;
    }

    @Override
    public UUID saveUserInCassandra(User user) {
        log.info("Save user {}", user);
        user.setRole(Role.ROLE_USER);
        User createdUser = userRepository.insert(user);
        log.info("User ID {}", createdUser.getUserKey().getId());
        return createdUser.getUserKey().getId();
    }
}
