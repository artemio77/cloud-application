package com.gmail.derevets.artem.usermanagementservice.service;

import com.gmail.derevets.artem.usermanagementservice.model.User;

import java.util.UUID;

public interface UserService {

    User findUserById(UUID id);

    UUID findUserByEmail(String email);

    User findUser(User user);

    UUID saveUserInCassandra(User user);
}
