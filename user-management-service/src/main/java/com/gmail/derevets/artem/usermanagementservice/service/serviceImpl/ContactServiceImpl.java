package com.gmail.derevets.artem.usermanagementservice.service.serviceImpl;

import com.fasterxml.uuid.Generators;
import com.gmail.derevets.artem.usermanagementservice.exception.contact.ContactRequestMapIllegalArgumentException;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.repository.ContactRepository;
import com.gmail.derevets.artem.usermanagementservice.service.ContactService;
import com.gmail.derevets.artem.usermanagementservice.util.ContactUtils;
import lombok.SneakyThrows;
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

    @Override
    @SneakyThrows(ContactRequestMapIllegalArgumentException.class)
    public UUID createContact(Map<String, String> contactMap) {
        UUID id = Generators.timeBasedGenerator().generate();
        log.info("Map {}", contactMap);
        ContactUtils.validateContactCreateMap(contactMap);
        ContactUtils.createContactMap(id, contactMap)
                .forEach((key, value) -> contactRepository.insert(value));
        return id;
    }

    @Override
    public Contact getContactById(UUID id) {
        return contactRepository.findById(id).get();

    }

    @Override
    public List<Contact> getContactByContactId(UUID id) {
        log.info("Contact Id {}", id);
        return contactRepository.findByContactKey_ContactId(id);
    }

    @Override
    public List<Contact> getContactByUser(User user) {
        return contactRepository.findByContactKey_ContactId(user.getId());
    }

    @Override
    public List<Contact> getContactByContactIdAndApprovedStatus(UUID id, Boolean approved) {
        return contactRepository.findByContactKey_ContactIdAndContactKey_Approved(id, approved);
    }
}
