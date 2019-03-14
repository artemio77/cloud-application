package com.gmail.derevets.artem.chatservice.service.serviceImpl;

import com.fasterxml.uuid.Generators;
import com.gmail.derevets.artem.chatservice.model.Message;
import com.gmail.derevets.artem.chatservice.model.type.MessageType;
import com.gmail.derevets.artem.chatservice.repository.MessageRepository;
import com.gmail.derevets.artem.chatservice.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findMessageByChatId(UUID chatId) {
        return messageRepository.findMessagesByMessageKey_ChatId(chatId);
    }

    @Override
    public void saveMessage(Message message) {
        message.getMessageKey().setId(Generators.timeBasedGenerator().generate());
        message.setCreationTime(LocalDateTime.now());
        message.setModificationTime(LocalDateTime.now());
        messageRepository.save(message);
    }
}
