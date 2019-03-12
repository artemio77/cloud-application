package com.gmail.derevets.artem.chatservice.service.serviceImpl;

import com.fasterxml.uuid.Generators;
import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.model.key.ChatKey;
import com.gmail.derevets.artem.chatservice.model.type.ChatType;
import com.gmail.derevets.artem.chatservice.repository.ChatRepository;
import com.gmail.derevets.artem.chatservice.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public UUID createPrivateChat(Map<String, String> contactMap) {
        UUID chatId = Generators.timeBasedGenerator().generate();
        LocalDateTime localDateTime = LocalDateTime.now();
        contactMap.forEach((key, value) -> {
            Chat chat = Chat.builder()
                    .chatKey(ChatKey.builder()
                            .id(chatId)
                            .chatType(ChatType.PRIVATE_CHAT)
                            .participant(UUID.fromString(value))
                            .build())
                    .build();
            chat.setCreationTime(localDateTime);
            chat.setModificationTime(localDateTime);
            chat.setVersion(0);
            log.info("CHAT CREATED {}", chat);
            chatRepository.insert(chat);
        });
        return chatId;
    }

    @Override
    public List<Chat> getByParticipantId(UUID id) {
        return chatRepository.findByChatKey_Participant(id);
    }

    @Override
    public List<Chat> getByParticipantIdAndChatType(UUID id, ChatType chatType) {
        return chatRepository.findByChatKey_ParticipantAndChatKey_ChatType(id, chatType);
    }
}
