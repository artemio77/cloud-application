package com.gmail.derevets.artem.usermanagementservice.clients;

import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.model.UserExternal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface UserClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/auth-service/authManagement/register",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserExternal createUser(@RequestBody UserExternal user);


    @RequestMapping(method = RequestMethod.POST, value = "/auth-service/authManagement/activate/{code}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    UserExternal activateUser(@PathVariable Long code);


}
