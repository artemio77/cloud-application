package com.gmail.derevets.artem.chatservice.controller;

import com.gmail.derevets.artem.chatservice.kafka.service.producer.MessageKafkaServiceProducer;
import com.gmail.derevets.artem.chatservice.model.Message;
import com.gmail.derevets.artem.chatservice.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/messageManagement")
public class MessageRESTController {


    @Autowired
    private MessageService messageService;

    @Autowired
    private MessageKafkaServiceProducer messageKafkaServiceProducer;

    @GetMapping("/get")
    public @ResponseBody
    List<Message> getMessageListByChatId(@RequestParam("chatId") UUID chatId) {
        return messageService.findMessageByChatId(chatId);
    }


    @PutMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#oauth2.hasScope('server')")
    public JSONObject createContact(@RequestParam("message-type") String messageType,
                                    @RequestBody Message message) throws JSONException {
        log.info("PUT MESSAGE {}", message);
        messageKafkaServiceProducer.sendMessage(message, messageType);
        return new JSONObject().put("response", "Created");
    }
}
