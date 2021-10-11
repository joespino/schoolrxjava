package com.pe.schoolreactive.repository;

import com.pe.schoolreactive.domain.User;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.Optional;

public interface UserRepository extends CassandraRepository<User, Integer>, CustomUserRepository {
    Optional<User> findUserByActiveAndUsername(Boolean status, String username);
}
