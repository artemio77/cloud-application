package com.gmail.derevets.artem.usermanagementservice.clients;

import com.gmail.derevets.artem.usermanagementservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface UserClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/auth-service/authManagement/register",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createUser(@RequestBody User user);
}
