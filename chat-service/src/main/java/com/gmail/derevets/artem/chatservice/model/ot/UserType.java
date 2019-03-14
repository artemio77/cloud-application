package com.gmail.derevets.artem.chatservice.model.ot;

import com.datastax.driver.core.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@UserDefinedType("contact_user")
@NoArgsConstructor
@AllArgsConstructor
public class UserType implements Serializable {

    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    @CassandraType(type = DataType.Name.TEXT)
    private String email;

    @CassandraType(type = DataType.Name.TEXT)
    private String firstName;

    @CassandraType(type = DataType.Name.TEXT)
    private String lastName;


}
