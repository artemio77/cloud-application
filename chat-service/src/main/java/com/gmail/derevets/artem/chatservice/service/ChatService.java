package com.gmail.derevets.artem.chatservice.service;


import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.model.type.ChatType;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ChatService {

    UUID createPrivateChatByMap(Map<String, String> contactMap);

    UUID createPrivateChat(Chat chat);

    List<Chat> getByParticipantId(UUID id);

    List<Chat> getByParticipantIdAndChatType(UUID id, ChatType chatType);
}
