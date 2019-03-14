package com.gmail.derevets.artem.usermanagementservice.model.key;

import com.datastax.driver.core.DataType;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@PrimaryKeyClass
@AllArgsConstructor
@NoArgsConstructor
public class ContactKey implements Serializable {

    @PrimaryKeyColumn(name = "id")
    private UUID id;

    @PrimaryKeyColumn(name = "contact_id", type = PrimaryKeyType.PARTITIONED)
    private UUID contactId;

    @PrimaryKeyColumn(name = "user_type_list")
    @CassandraType(type = DataType.Name.UDT, userTypeName = "contact_user")
    private List<UserType> userTypeList;
}
