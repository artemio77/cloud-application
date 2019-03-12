package com.gmail.derevets.artem.authservice.service;

import com.gmail.derevets.artem.authservice.model.User;

public interface UserService {

    User registerNewAccount(final User user);

    User findUserByEmail(final String email);

    void updateUser(User user);

    User activateUser(Long verificationCode);

    String checkUserVerificationCode(Long verificationCode);

}
