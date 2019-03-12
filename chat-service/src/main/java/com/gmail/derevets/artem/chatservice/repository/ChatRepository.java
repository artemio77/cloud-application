package com.gmail.derevets.artem.chatservice.repository;

import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.model.type.ChatType;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ChatRepository extends CassandraRepository<Chat, UUID> {


    List<Chat> findByChatKey_Participant(UUID id);

    List<Chat> findByChatKey_ChatType(ChatType chatType);

    List<Chat> findByChatKey_ParticipantAndChatKey_ChatType(UUID id, ChatType chatType);
}
