package com.gmail.derevets.artem.usermanagementservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.gmail.derevets.artem.usermanagementservice.model.key.UserKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Artem Derevets
 */
@Data
@Builder
@Table("user")
@JsonPropertyOrder({
        "id", "email", "firstName", "lastName"
})
@AllArgsConstructor
public class User extends BaseEntity<UUID> implements Serializable {

    @PrimaryKey
    private UserKey userKey;

    @JsonProperty("firstName")
    @Column("first_name")
    private String firstName;

    @JsonProperty("lastName")
    @Column("last_name")
    private String lastName;

    @Transient
    @JsonProperty("password")
    private String password;

    private Role role;

    @Column("is_enabled")
    private Boolean isEnabled;

    @Transient
    private Long verificationCode;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "userKey=" + userKey +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", verificationCode=" + verificationCode +
                '}';
    }
}
