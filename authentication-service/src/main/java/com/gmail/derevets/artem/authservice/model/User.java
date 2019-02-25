package com.gmail.derevets.artem.authservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.gmail.derevets.artem.authservice.model.enums.Role;
import com.gmail.derevets.artem.authservice.model.jsonView.UserView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Artem Derevets
 */
@Entity
@Builder
@Table(name = "user_accounts")
@Data
@JsonPropertyOrder({
        "id", "email", "firstName", "lastName", "password"
})
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<UUID> implements Serializable {

    @JsonProperty("email")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    @JsonView(UserView.ShortView.class)
    private String email;

    @JsonProperty("firstName")
    @Column(name = "first_name", length = 100, nullable = false)
    @JsonView(UserView.FullView.class)
    private String firstName;

    @JsonProperty("lastName")
    @Column(name = "last_name", length = 100, nullable = false)
    @JsonView(UserView.FullView.class)
    private String lastName;

    @JsonProperty("password")
    @Column(name = "password", length = 255)
    @JsonView(UserView.FullView.class)
    private String password;

    @JsonProperty("role")
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    @Column(name = "is_account_non_locked", nullable = false)
    private Boolean isAccountNonLocked;

    @Column(name = "is_account_non_expired", nullable = false)
    private Boolean isAccountNonExpired;

    @Column(name = "is_credentials_non_expired", nullable = false)
    private Boolean isCredentialsNonLocked;


    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @Column(name = "verification_code", unique = true)
    private Long verificationCode;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", super.getId())
                .append("creationTime", this.getCreationTime())
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("modificationTime", this.getModificationTime())
                .append("version", this.getVersion())
                .append("role" + this.getRole())
                .toString();
    }
}
