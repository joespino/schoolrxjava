package com.pe.schoolreactive.domain;

import com.pe.schoolreactive.domain.type.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Map;
import java.util.Set;

@Table(value = "student")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @PrimaryKey
    @Id
    private Integer idstudent;
    private Integer iduser;
    private Set<Course> courses;
    private Map<Integer, Integer> notes;
    private String address;
    private Boolean active;
    private String userregister;
    private String usermodified;
}
