package com.pe.schoolreactive.repository;

import com.pe.schoolreactive.domain.Teacher;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CassandraRepository<Teacher, Integer> {
}
