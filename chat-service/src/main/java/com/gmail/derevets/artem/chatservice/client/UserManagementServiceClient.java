package com.gmail.derevets.artem.chatservice.client;

import com.gmail.derevets.artem.chatservice.model.ot.UserType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "user-management-service")
public interface UserManagementServiceClient {


    @GetMapping(value = "/user-management-service/rest/get-user-type")
    UserType getUserType(@RequestParam("userId") UUID id);


}
