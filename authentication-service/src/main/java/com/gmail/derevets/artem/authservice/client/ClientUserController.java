package com.gmail.derevets.artem.authservice.client;

import com.gmail.derevets.artem.authservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service", fallback = UserFallback.class)
public interface ClientUserController {
    @RequestMapping(method = RequestMethod.PUT, value = "/auth-service/authManagement/register",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    void createUser(User user);


    @RequestMapping(method = RequestMethod.GET, value = "/auth-service/authManagement/user/{email:.+}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    User getUser(@PathVariable("email") String email);
}
