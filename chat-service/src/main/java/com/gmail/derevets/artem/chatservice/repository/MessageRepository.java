package com.gmail.derevets.artem.chatservice.repository;

import com.gmail.derevets.artem.chatservice.model.Message;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends CassandraRepository<Message, UUID> {

    List<Message> findMessagesByMessageKey_ChatId(UUID id);


}
