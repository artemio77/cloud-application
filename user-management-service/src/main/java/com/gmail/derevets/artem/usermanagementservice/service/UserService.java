package com.gmail.derevets.artem.usermanagementservice.service;

import com.gmail.derevets.artem.usermanagementservice.exception.user.UserNotFoundException;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User findUserById(UUID id) throws UserNotFoundException;

    User findUserByEmail(String email);

    User findUser(User user);

    UUID saveUserInCassandra(User user);
}
