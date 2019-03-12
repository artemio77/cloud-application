package com.gmail.derevets.artem.chatservice.model.key;

import com.gmail.derevets.artem.chatservice.model.type.ChatType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@PrimaryKeyClass
public class ChatKey implements Serializable {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @PrimaryKeyColumn(name = "participant", type = PrimaryKeyType.CLUSTERED)
    private UUID participant;

    @PrimaryKeyColumn(value = "chat_type", type = PrimaryKeyType.CLUSTERED)
    private ChatType chatType;


}
