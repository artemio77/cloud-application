package com.gmail.derevets.artem.chatservice.model;

import com.gmail.derevets.artem.chatservice.model.key.ChatKey;
import com.gmail.derevets.artem.chatservice.model.type.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@Table("chat")
@NoArgsConstructor
@AllArgsConstructor
public class Chat extends BaseEntity<UUID> {

    @PrimaryKey
    private ChatKey chatKey;
    @Column("description")
    private String description;


}
