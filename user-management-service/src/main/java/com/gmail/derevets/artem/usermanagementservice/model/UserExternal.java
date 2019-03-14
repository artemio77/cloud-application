package com.gmail.derevets.artem.usermanagementservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.UUID;


@Data
@Builder
@JsonPropertyOrder({
        "id", "email", "firstName", "lastName"
})
@AllArgsConstructor
public class UserExternal extends BaseEntity<UUID> implements Serializable {

    private UUID id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    @Column("firstname")
    private String firstName;

    @JsonProperty("lastName")
    @Column("lastname")
    private String lastName;

    @Transient
    @JsonProperty("password")
    private String password;

    private Boolean isEnabled;

    private Role role;

    @Transient
    private Long verificationCode;

    public UserExternal() {
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
