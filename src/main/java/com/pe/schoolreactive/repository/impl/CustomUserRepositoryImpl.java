package com.pe.schoolreactive.repository.impl;

import com.pe.schoolreactive.domain.User;
import com.pe.schoolreactive.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.data.cassandra.core.query.Update;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Slf4j
@Repository
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final CassandraTemplate template;

    public Boolean updateStatus(Integer id, Boolean status) {
        Query query = Query.query(Criteria.where("iduser").is(id));
        Update update = Update.empty().set("active" , status);
        return template.update(query, update, User.class);
    }
}
