package com.suv.reactive.repository;

import java.util.UUID;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.suv.reactive.repository.entity.PersonEntity;

@Repository
public interface PersonRepository extends ReactiveCassandraRepository<PersonEntity, UUID> {
}
