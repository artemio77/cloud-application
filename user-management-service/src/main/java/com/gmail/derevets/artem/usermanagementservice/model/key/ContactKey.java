package com.gmail.derevets.artem.usermanagementservice.model.key;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@PrimaryKeyClass
public class ContactKey implements Serializable {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.CLUSTERED)
    private UUID id;

    @PrimaryKeyColumn(name = "contactId", type = PrimaryKeyType.PARTITIONED)
    private UUID contactId;

    @PrimaryKeyColumn(name = "approved", type = PrimaryKeyType.CLUSTERED)
    private boolean approved;

}
