package com.gmail.derevets.artem.usermanagementservice.controller;

import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;
import com.gmail.derevets.artem.usermanagementservice.service.UserService;
import com.gmail.derevets.artem.usermanagementservice.util.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/rest")
public class UserRESTController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserUtils userUtils;


    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    User getUserById(@RequestParam("userId") String id) {


        return userService.findUserById(UUID.fromString(id));
    }

    @GetMapping("/get/email")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    User getUserByEmail(@RequestParam("email") String email) {
        return userService.findUserByEmail(email);

    }

    @GetMapping("/get-user-type")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody
    UserType getUserTypeById(@RequestParam("userId") UUID id) {
        return userUtils.transformUserToCassandraUserType(userService.findUserById(id));
    }


}
