package com.suv.reactive.repository;

import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

import com.suv.reactive.repository.entity.PersonByCountryEntity;
import com.suv.reactive.repository.entity.PersonByCountryKey;

import reactor.core.publisher.Flux;

@Repository
public interface PersonByCountryRepository extends ReactiveCassandraRepository<PersonByCountryEntity, PersonByCountryKey> {

  Flux<PersonByCountryEntity> findAllByKeyCountry(final String country);
}
