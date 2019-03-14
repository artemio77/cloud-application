package com.gmail.derevets.artem.usermanagementservice.model;

import com.datastax.driver.core.DataType;
import com.gmail.derevets.artem.usermanagementservice.model.cassandra.ot.UserType;
import com.gmail.derevets.artem.usermanagementservice.model.key.ContactKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@Table("contact")
@NoArgsConstructor
@AllArgsConstructor
public class Contact extends BaseEntity<UUID> implements Serializable {

    @PrimaryKey
    private ContactKey contactKey;


}
