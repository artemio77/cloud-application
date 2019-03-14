package com.gmail.derevets.artem.chatservice.model;

import com.gmail.derevets.artem.chatservice.model.key.MessageKey;
import com.gmail.derevets.artem.chatservice.model.type.MessageType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;

@Data
@Builder
@Table("message")
public class Message extends BaseEntity<MessageKey> implements Serializable {

    @PrimaryKey
    private MessageKey messageKey;

    @Column("message_text")
    private String messageText;

    @Column("message_type")
    private MessageType messageType;
}
