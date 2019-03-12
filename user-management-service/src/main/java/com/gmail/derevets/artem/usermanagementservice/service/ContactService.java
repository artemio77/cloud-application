package com.gmail.derevets.artem.usermanagementservice.service;

import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ContactService {

    UUID createContact(Map<String, String> contactMap);

    Contact getContactById(UUID id);

    List<Contact> getContactByContactId(UUID id);

    List<Contact> getContactByUser(User user);

    List<Contact> getContactByContactIdAndApprovedStatus(UUID id,Boolean approved);
}
