package com.gmail.derevets.artem.usermanagementservice.controller;

import com.gmail.derevets.artem.usermanagementservice.clients.ChatClient;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/contact")
public class ContactRESTController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private ChatClient chatClient;


    @PutMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("#oauth2.hasScope('server')")
    public JSONObject createContact(@RequestParam Map<String, String> requestParams) {
        requestParams.remove("access_token");
        log.info("REQUEST PARAMETERS {}", requestParams);
        UUID contactId = contactService.createContact(requestParams);
        String chat = chatClient.createChat(requestParams);
        log.info("CHAT {}", chat);
        return new JSONObject().put("contactId", contactId);
    }


    @GetMapping("/get")
    public @ResponseBody
    List<Contact> getContactByContactId(@RequestParam("contactId") String contactId) {
        List<Contact> contact = contactService.getContactByContactId(UUID.fromString(contactId));
        log.info("GET CONTACT {}", contact);
        return contact;
    }

    @GetMapping("/get/state")
    public @ResponseBody
    List<Contact> getContactByContactIdAndApprovedState(@RequestParam("contactId") String contactId,
                                                        @RequestParam("approved") Boolean approved) {
        List<Contact> contact =
                contactService.getContactByContactIdAndApprovedStatus(
                        UUID.fromString(contactId),
                        approved);
        log.info("GET CONTACT {}", contact);
        return contact;
    }
}
