package com.gmail.derevets.artem.usermanagementservice.kafka.service.producer;

import com.gmail.derevets.artem.usermanagementservice.kafka.service.KafkaManager;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactKafkaServiceProducer {


    @Autowired
    private KafkaTemplate<String, Contact> contactKafkaTemplate;


    public void sendContact(Contact contact) {

        log.info(String.format("#### -> Produced message -> %s", contact));
        contactKafkaTemplate.send(KafkaManager.CONTACT_CREATE_TOPIC, contact);
    }


}
