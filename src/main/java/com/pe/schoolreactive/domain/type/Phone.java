package com.pe.schoolreactive.domain.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Column(value = "country_code")
    private Integer countrycode;
    private String number;
}
