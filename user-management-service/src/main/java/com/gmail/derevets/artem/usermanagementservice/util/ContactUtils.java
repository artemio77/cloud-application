package com.gmail.derevets.artem.usermanagementservice.util;

import com.gmail.derevets.artem.usermanagementservice.exception.contact.ContactRequestMapIllegalArgumentException;
import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.model.User;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;
import com.gmail.derevets.artem.usermanagementservice.model.key.ContactKey;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContactUtils {

    public void validateContactCreateMap(Map<String, String> contactMap) throws ContactRequestMapIllegalArgumentException {
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

    public Contact createNewContactEntity(UUID id, UUID contactId,
                                          List<UserType> userType) {
        LocalDateTime localDateTime = LocalDateTime.now();
        Contact contact = Contact.builder()
                .contactKey(ContactKey.builder()
                        .id(id)
                        .contactId(contactId)
                        .userTypeList(userType)
                        .build())
                .build();
        contact.setModificationTime(localDateTime);
        contact.setCreationTime(localDateTime);
        contact.setVersion(0);
        log.info("Created contact {}", contact);
        return contact;
    }

    public UserType transformUserToCassandraUserType(User user) {
        log.info("CONTACT USER {}", user);
        DozerBeanMapper mapper = new DozerBeanMapper();
        UserType userType = mapper.map(user, UserType.class);
        userType.setEmail(user.getUserKey().getEmail());
        userType.setId(user.getUserKey().getId());
        return userType;
    }


}
