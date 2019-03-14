package com.gmail.derevets.artem.usermanagementservice.model.key;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@PrimaryKeyClass
@AllArgsConstructor
@NoArgsConstructor
public class UserKey implements Serializable {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.CLUSTERED)
    @JsonProperty("email")
    private String email;
}
