package com.gmail.derevets.artem.usermanagementservice.clients;

import com.gmail.derevets.artem.usermanagementservice.model.User;
import org.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@FeignClient(name = "chat-service")
public interface ChatClient {

    @RequestMapping(method = RequestMethod.PUT, value = "/chat-service/chatManagement/create",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    String createChat(@RequestParam Map<String, String> requestParams);
}


