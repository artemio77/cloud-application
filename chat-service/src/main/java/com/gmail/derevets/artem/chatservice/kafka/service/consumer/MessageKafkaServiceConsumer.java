package com.gmail.derevets.artem.chatservice.kafka.service.consumer;

import com.gmail.derevets.artem.chatservice.kafka.service.KafkaManager;
import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.model.Message;
import com.gmail.derevets.artem.chatservice.service.ChatService;
import com.gmail.derevets.artem.chatservice.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class MessageKafkaServiceConsumer {


    @Autowired
    private MessageService messageService;


    @KafkaListener(topics = KafkaManager.MESSAGE_CREATE_TOPIC,
            containerFactory = "messageKafkaListenerContainerFactory",
            groupId = "group_id")
    public void consumerContactCreate(Message message) {
        log.info(String.format("#### -> Consumed message -> %s", message));
        UUID id = messageService.saveMessage(message);
        log.info("CREATE MESSAGE ID {}", id);
    }
}
