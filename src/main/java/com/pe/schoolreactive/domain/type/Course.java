package com.pe.schoolreactive.domain.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.Map;

@UserDefinedType
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    private Integer idcourse;
    private String name;
    private Map<String, String> information;
    private String address;
    private Boolean active;
    private String userregister;
    private String usermodified;
}
