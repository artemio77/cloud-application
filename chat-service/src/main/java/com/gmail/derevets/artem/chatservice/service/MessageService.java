package com.gmail.derevets.artem.chatservice.service;

import com.gmail.derevets.artem.chatservice.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {

    List<Message> findMessageByChatId(UUID chatId);

    UUID saveMessage(Message message);

}
