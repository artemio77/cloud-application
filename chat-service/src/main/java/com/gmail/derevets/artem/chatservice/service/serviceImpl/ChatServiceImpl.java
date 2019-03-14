package com.gmail.derevets.artem.chatservice.service.serviceImpl;

import com.fasterxml.uuid.Generators;
import com.gmail.derevets.artem.chatservice.client.UserManagementServiceClient;
import com.gmail.derevets.artem.chatservice.kafka.service.producer.ChatKafkaServiceProducer;
import com.gmail.derevets.artem.chatservice.model.Chat;
import com.gmail.derevets.artem.chatservice.model.key.ChatKey;
import com.gmail.derevets.artem.chatservice.model.ot.UserType;
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
import java.util.stream.Collectors;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private ChatKafkaServiceProducer chatKafkaServiceProducer;


    @Autowired
    private UserManagementServiceClient userManagementServiceClient;

    @Override
    public UUID createPrivateChatByMap(Map<String, String> contactMap) {
        UUID chatId = Generators.timeBasedGenerator().generate();
        LocalDateTime localDateTime = LocalDateTime.now();
        List<UserType> userTypeList = contactMap.entrySet().stream()
                .map(id ->
                        userManagementServiceClient.getUserType(UUID.fromString(id.getValue())))
                .collect(Collectors.toList());

        contactMap.forEach((key, value) -> {
            Chat chat = Chat.builder()
                    .chatKey(ChatKey.builder()
                            .id(chatId)
                            .chatType(ChatType.PRIVATE_CHAT)
                            .participant(UUID.fromString(value))
                            .userTypeList(userTypeList)
                            .build())
                    .build();
            chat.setCreationTime(localDateTime);
            chat.setModificationTime(localDateTime);
            chat.setVersion(0);
            log.info("CHAT CREATED {}", chat);
            chatKafkaServiceProducer.sendChat(chat);
        });
        return chatId;
    }

    @Override
    public UUID createPrivateChat(Chat chat) {
        return chatRepository.save(chat).getChatKey().getId();
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
