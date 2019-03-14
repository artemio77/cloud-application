package com.gmail.derevets.artem.chatservice.controller;

import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.model.type.ChatType;
import com.gmail.derevets.artem.chatservice.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/chatManagement")
public class ChatRESTController {


    @Autowired
    private ChatService chatService;


    @GetMapping("/get/filter/chat-type")
    public @ResponseBody
    List<Chat> getChatListByParticipantIdAndChatType(@RequestParam("participantId") UUID participantId,
                                                     @RequestParam("chat-type") ChatType chatType) {
        return chatService.getByParticipantIdAndChatType(participantId, chatType);
    }

    @GetMapping("/get")
    public @ResponseBody
    List<Chat> getChatListByParticipantId(@RequestParam("participantId") UUID participantId) {
        return chatService.getByParticipantId(participantId);
    }

    @PutMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#oauth2.hasScope('server')")
    public String createContact(@RequestParam Map<String, String> requestParams) throws JSONException {
        log.info("PUT CHAT {}", requestParams);
        UUID id = chatService.createPrivateChatByMap(requestParams);
        return id.toString();
    }
}
