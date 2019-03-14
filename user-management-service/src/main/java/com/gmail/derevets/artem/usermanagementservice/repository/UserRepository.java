package com.gmail.derevets.artem.usermanagementservice.repository;


import com.gmail.derevets.artem.usermanagementservice.model.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends CassandraRepository<User, UUID> {

    User findByUserKey_Id(UUID id);

    User findByUserKey_Email(String email);
}
