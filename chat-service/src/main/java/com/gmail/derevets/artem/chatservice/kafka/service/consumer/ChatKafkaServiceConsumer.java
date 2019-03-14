package com.gmail.derevets.artem.chatservice.kafka.service.consumer;

import com.gmail.derevets.artem.chatservice.kafka.service.KafkaManager;
import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ChatKafkaServiceConsumer {


    @Autowired
    private ChatService chatService;


    @KafkaListener(topics = KafkaManager.CHAT_CREATE_TOPIC,
            containerFactory = "chatKafkaListenerContainerFactory",
            groupId = "group_id")
    public void consumerContactCreate(Chat chat) {
        log.info(String.format("#### -> Consumed message -> %s", chat));
        UUID id = chatService.createPrivateChat(chat);
        log.info("CREATE CHAT ID {}", id);
    }




}
