package com.pe.schoolreactive.domain;

import com.pe.schoolreactive.domain.type.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Map;
import java.util.Set;

@Table(value = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @PrimaryKey
    @Id
    private Integer iduser;
    private String username;
    private String password;
    private String name;
    private Set<String> email;
    private Map<String, Phone> phone;
    private String address;
    private Boolean active;
    private String userregister;
    private String usermodified;
}
