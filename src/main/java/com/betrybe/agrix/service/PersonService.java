package com.betrybe.agrix.service;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.repository.PersonRepository;
import com.betrybe.agrix.service.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer class for handling persons business logic.
 */
@Service
public class PersonService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(
      PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  /**
   * Returns a person for a given ID.
   */
  public Person getPersonById(Long id) throws PersonNotFoundException {
    return personRepository.findById(id)
        .orElseThrow(PersonNotFoundException::new);
  }

  /**
   * Returns a person for a given username.
   */
  public Person getPersonByUsername(String username) throws PersonNotFoundException {
    return personRepository.findByUsername(username)
        .orElseThrow(PersonNotFoundException::new);
  }

  /**
   * Creates a new person.
   */
  public Person create(Person person) {
    return personRepository.save(person);
  }
}