package com.gmail.derevets.artem.usermanagementservice.kafka.service.counsumer;

import com.gmail.derevets.artem.usermanagementservice.kafka.service.KafkaManager;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class ContactKafkaServiceConsumer {


    @Autowired
    private ContactService contactService;


    @KafkaListener(topics = KafkaManager.CONTACT_CREATE_TOPIC,
            containerFactory = "contactKafkaListenerContainerFactory",
            groupId = "group_id")
    public void consumerContactCreate(Contact contact) {
        log.info(String.format("#### -> Consumed message -> %s", contact));
        UUID id = contactService.createContact(contact);
        log.info("Update USER ID {}", id);
    }

}
