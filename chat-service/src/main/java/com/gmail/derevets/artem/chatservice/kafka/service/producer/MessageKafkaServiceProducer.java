package com.gmail.derevets.artem.chatservice.kafka.service.producer;

import com.gmail.derevets.artem.chatservice.kafka.service.KafkaManager;
import com.gmail.derevets.artem.chatservice.model.Message;
import com.gmail.derevets.artem.chatservice.model.type.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageKafkaServiceProducer {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public void sendMessage(Message message, String messageType) {
        message.setMessageType(MessageType.valueOf(messageType));
        kafkaTemplate.send(KafkaManager.MESSAGE_CREATE_TOPIC, message);
    }


}
