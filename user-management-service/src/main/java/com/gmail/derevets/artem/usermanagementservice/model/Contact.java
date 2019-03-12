package com.gmail.derevets.artem.usermanagementservice.model;

import com.gmail.derevets.artem.usermanagementservice.model.key.ContactKey;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
@Builder
@Table("contact")
public class Contact extends BaseEntity<UUID> {

    @PrimaryKey
    private ContactKey contactKey;

}
