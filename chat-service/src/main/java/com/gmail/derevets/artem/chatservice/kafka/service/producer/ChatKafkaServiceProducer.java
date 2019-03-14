package com.gmail.derevets.artem.chatservice.kafka.service.producer;

import com.gmail.derevets.artem.chatservice.kafka.service.KafkaManager;
import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ChatKafkaServiceProducer {


    @Autowired
    private KafkaTemplate<String, Chat> kafkaTemplate;

    public void sendChat(Chat chat) {
        kafkaTemplate.send(KafkaManager.CHAT_CREATE_TOPIC, chat);
    }
}
