package com.gmail.derevets.artem.chatservice.model.key;

import com.datastax.driver.core.DataType;
import com.gmail.derevets.artem.chatservice.model.ot.UserType;
import com.gmail.derevets.artem.chatservice.model.type.ChatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@PrimaryKeyClass
@NoArgsConstructor
@AllArgsConstructor
public class ChatKey implements Serializable {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @PrimaryKeyColumn(name = "participant", type = PrimaryKeyType.CLUSTERED)
    private UUID participant;

    @PrimaryKeyColumn(value = "chat_type", type = PrimaryKeyType.CLUSTERED)
    private ChatType chatType;

    @PrimaryKeyColumn(name = "user_type_list")
    @CassandraType(type = DataType.Name.UDT, userTypeName = "contact_user")
    private List<UserType> userTypeList;

}
