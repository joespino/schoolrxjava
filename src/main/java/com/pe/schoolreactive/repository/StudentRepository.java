package com.pe.schoolreactive.repository;

import com.pe.schoolreactive.domain.Student;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CassandraRepository<Student, Integer> {
}
