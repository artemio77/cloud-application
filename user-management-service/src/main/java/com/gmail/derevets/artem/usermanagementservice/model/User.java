package com.gmail.derevets.artem.usermanagementservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Artem Derevets
 */
@Data
@JsonPropertyOrder({
        "id", "email", "firstName", "lastName", "password"
})
public class User extends BaseEntity<UUID> implements Serializable {

    @JsonProperty("email")
    private String email;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role")

    private Role role;

    private Boolean isAccountNonLocked;

    private Boolean isAccountNonExpired;

    private Boolean isCredentialsNonLocked;

    private Boolean isEnabled;

    private Long verificationCode;

    public User() {
    }


    public static Builder getBuilder() {
        return new Builder();
    }


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

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
            user.role = Role.ROLE_USER;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public Builder isCredentialsNonLocked(Boolean isCredentialsNonLocked) {
            user.isCredentialsNonLocked = isCredentialsNonLocked;
            return this;
        }

        public Builder isAccountNonLocked(Boolean isAccountNonLocked) {
            user.isAccountNonLocked = isAccountNonLocked;
            return this;
        }

        public Builder isAccountNonExpired(Boolean isAccountNonExpired) {
            user.isAccountNonExpired = isAccountNonExpired;
            return this;
        }

        public Builder isEnabled(Boolean isEnabled) {
            user.isEnabled = isEnabled;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
