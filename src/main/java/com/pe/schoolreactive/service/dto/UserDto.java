package com.pe.schoolreactive.service.dto;

import com.pe.schoolreactive.domain.type.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Set<String> email;
    private Map<String, Phone> phone;
}
