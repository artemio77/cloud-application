package com.gmail.derevets.artem.usermanagementservice.repository;

import com.gmail.derevets.artem.usermanagementservice.model.Contact;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends CassandraRepository<Contact, UUID> {

    List<Contact> findByContactKey_ContactId(UUID id);

    List<Contact> findByContactKey_UserTypeList(UserType userType);

}
