package com.gmail.derevets.artem.usermanagementservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Artem Derevets
 */
@Data
@Builder
@Table(name = "user")
@JsonPropertyOrder({
        "id", "email", "firstName", "lastName", "password"
})
@AllArgsConstructor
public class User extends BaseEntity<UUID> implements Serializable {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private UUID id;

    @PrimaryKeyColumn(name = "email", type = PrimaryKeyType.CLUSTERED)
    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @Transient
    @JsonProperty("password")
    private String password;

    private Boolean isEnabled;

    private Long verificationCode;

    public User() {
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.getId())
                .append("creationTime", this.getCreationTime())
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("modificationTime", this.getModificationTime())
                .append("version", this.getVersion())
                .toString();
    }
}
