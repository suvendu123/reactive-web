package com.suv.reactive.service;

import static com.suv.reactive.service.PersonMapper.toPersonByCountryEntity;
import static com.suv.reactive.service.PersonMapper.toPersonEntity;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.suv.reactive.repository.PersonByCountryRepository;
import com.suv.reactive.repository.PersonRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class PersonManager {

  private final PersonRepository personRepository;
  private final PersonByCountryRepository personByCountryRepository;

  public PersonManager(
      PersonRepository personRepository, PersonByCountryRepository personByCountryRepository) {
    this.personRepository = personRepository;
    this.personByCountryRepository = personByCountryRepository;
  }

  public Flux<Person> findAll() {
    return personByCountryRepository.findAll().map(PersonMapper::toPerson);
  }

  public Flux<Person> findAllByCountry(String country) {
    return personByCountryRepository.findAllByKeyCountry(country).map(PersonMapper::toPerson);
  }

  public Mono<Person> findById(final UUID id) {
    return personRepository.findById(id).map(PersonMapper::toPerson).switchIfEmpty(Mono.empty());
  }

  public Mono<Person> save(Person person) {
    return Mono.fromSupplier(
        () -> {
          personRepository
              .save(toPersonEntity(person))
              .and(personByCountryRepository.save(toPersonByCountryEntity(person)))
              .subscribe();
          return person;
        });
  }

  public Mono<Person> update(Person old, Person updated) {
    return Mono.fromSupplier(
        () -> {
          personRepository
              .save(toPersonEntity(updated))
              .and(personByCountryRepository.delete(toPersonByCountryEntity(old)))
              .and(personByCountryRepository.save(toPersonByCountryEntity(updated)))
              .subscribe();
          return updated;
        });
  }

  public Mono<Void> delete(Person person) {
    return Mono.fromSupplier(
        () -> {
          personRepository
              .delete(toPersonEntity(person))
              .and(personByCountryRepository.delete(toPersonByCountryEntity(person)))
              .subscribe();
          return null;
        });
  }
}
