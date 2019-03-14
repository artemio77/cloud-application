package com.gmail.derevets.artem.usermanagementservice.util;

import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.model.UserExternal;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;
import com.gmail.derevets.artem.usermanagementservice.model.key.UserKey;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

@Service
public class UserUtils {


    public User transformUserExternalToUser(UserExternal user) {
        return User.builder()
                .userKey(UserKey.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .build())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .isEnabled(user.getIsEnabled())
                .verificationCode(user.getVerificationCode())
                .build();

    }

    public UserExternal transformUserToUserExternal(UserExternal user) {
        return UserExternal.builder().build();
    }


    public UserType transformUserToCassandraUserType(User user) {
        DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
        UserType userType = dozerBeanMapper.map(user, UserType.class);
        userType.setId(user.getUserKey().getId());
        userType.setEmail(user.getUserKey().getEmail());
        return userType;
    }
}
