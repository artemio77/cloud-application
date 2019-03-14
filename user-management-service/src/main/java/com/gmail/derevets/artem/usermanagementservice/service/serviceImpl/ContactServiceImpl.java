package com.gmail.derevets.artem.usermanagementservice.service.serviceImpl;

import com.fasterxml.uuid.Generators;
import com.gmail.derevets.artem.usermanagementservice.exception.contact.ContactRequestMapIllegalArgumentException;
import com.gmail.derevets.artem.usermanagementservice.exception.user.UserNotFoundException;
import com.gmail.derevets.artem.usermanagementservice.kafka.service.producer.ContactKafkaServiceProducer;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;
import com.gmail.derevets.artem.usermanagementservice.repository.ContactRepository;
import com.gmail.derevets.artem.usermanagementservice.service.ContactService;
import com.gmail.derevets.artem.usermanagementservice.service.UserService;
import com.gmail.derevets.artem.usermanagementservice.util.ContactUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ContactKafkaServiceProducer contactKafkaServiceProducer;

    @Autowired
    private ContactUtils contactUtils;

    @Override
    public UUID createContactByMap(Map<String, String> contactMap) throws ContactRequestMapIllegalArgumentException, UserNotFoundException {
        UUID id = Generators.timeBasedGenerator().generate();
        log.info("Map {}", contactMap);
        contactUtils.validateContactCreateMap(contactMap);
        List<UserType> userTypeList = contactMap.entrySet().stream()
                .map(userId ->
                {
                    try {
                        return contactUtils.transformUserToCassandraUserType(
                                userService.findUserById(UUID.fromString(userId.getValue())));
                    } catch (UserNotFoundException e) {
                        log.error("User Not Found", e);
                        throw e;
                    }
                })
                .collect(Collectors.toList());
        contactMap.forEach((key, value) ->
                contactKafkaServiceProducer.sendContact(
                        contactUtils.createNewContactEntity(id, UUID.fromString(value), userTypeList)));
        return id;
    }

    @Override
    public UUID createContact(Contact contact) {
        return contactRepository.save(contact).getContactKey().getId();
    }

    @Override
    public Contact getContactById(UUID id) {
        return contactRepository.findById(id).get();

    }

    @Override
    public List<Contact> getContactByContactId(UUID id) throws UserNotFoundException {
        log.info("Contact Id {}", id);
        return contactRepository.findByContactKey_ContactId(id);
    }

    @Override
    public List<Contact> getContactByUser(User user) {
        return null;
    }

    @Override
    public List<Contact> getContactByUserType(UUID id) {
        UserType userType = contactUtils.transformUserToCassandraUserType(userService.findUserById(id));
        return contactRepository.findByContactKey_UserTypeList(userType);
    }

}
