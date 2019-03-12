package com.gmail.derevets.artem.usermanagementservice.util;

import com.gmail.derevets.artem.usermanagementservice.exception.contact.ContactRequestMapIllegalArgumentException;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.model.key.ContactKey;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class ContactUtils {

    public static void validateContactCreateMap(Map<String, String> contactMap) throws ContactRequestMapIllegalArgumentException {
        try {
            if (contactMap.size() != 2) {
                throw new ContactRequestMapIllegalArgumentException("Error contactMap size");
            }
            boolean exists = contactMap.keySet().containsAll(Arrays.asList("reciever", "initiator"));
            if (!exists) {
                throw new ContactRequestMapIllegalArgumentException("Error contactMap request parameters");
            }
        } catch (ContactRequestMapIllegalArgumentException сontactRequestMapIllegalArgumentException) {
            log.error("Error validate contact map");
            throw (сontactRequestMapIllegalArgumentException);
        }
    }


    public static Map<ContactKey, Contact> createContactMap(UUID id, Map<String, String> contactMap) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Map<ContactKey, Contact> userContactMap = contactMap.entrySet().stream()
                .map(key -> {
                    if (key.getKey().equalsIgnoreCase("initiator")) {
                        return createNewContactEntity(id, UUID.fromString(key.getValue())
                                , true, localDateTime);
                    } else {
                        return createNewContactEntity(id, UUID.fromString(key.getValue()),
                                false, localDateTime);
                    }
                })
                .collect(Collectors.toMap(
                        contact -> contact.getContactKey(),
                        contact -> contact));
        log.info("userContactMap {}", userContactMap);
        return userContactMap;
    }

    private static Contact createNewContactEntity(UUID id,
                                                  UUID contactValue,
                                                  Boolean approved,
                                                  LocalDateTime localDateTime) {
        Contact contact = Contact.builder()
                .contactKey(ContactKey.builder()
                        .id(id)
                        .contactId(contactValue)
                        .approved(approved)
                        .build())
                .build();
        contact.setModificationTime(localDateTime);
        contact.setCreationTime(localDateTime);
        contact.setVersion(0);
        log.info("Created contact {}", contact);
        return contact;
    }
}
