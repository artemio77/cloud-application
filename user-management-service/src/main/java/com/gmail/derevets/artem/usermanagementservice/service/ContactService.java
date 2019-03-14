package com.gmail.derevets.artem.usermanagementservice.service;

import com.gmail.derevets.artem.usermanagementservice.exception.contact.ContactRequestMapIllegalArgumentException;
import com.gmail.derevets.artem.usermanagementservice.exception.user.UserNotFoundException;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.model.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ContactService {

    UUID createContactByMap(Map<String, String> contactMap) throws ContactRequestMapIllegalArgumentException;

    UUID createContact(Contact contact);

    Contact getContactById(UUID id);

    List<Contact> getContactByContactId(UUID id) throws UserNotFoundException;

    List<Contact> getContactByUser(User user);

    List<Contact> getContactByUserType(UUID id);
}
