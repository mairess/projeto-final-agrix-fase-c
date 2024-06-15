package com.betrybe.agrix.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.repository.PersonRepository;
import com.betrybe.agrix.service.PersonService;
import com.betrybe.agrix.service.exception.PersonNotFoundException;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceTest {

  @MockBean
  PersonRepository personRepository;

  @Autowired
  PersonService personService;

  @Test
  @Description("Gets a person by personID.")
  public void testGetPersonById() throws PersonNotFoundException {
    Person person = new Person();
    person.setId(123L);
    person.setUsername("mangusto");
    person.setPassword("123456");
    person.setRole("USER");

    Mockito.when(personRepository.findById(eq(123L)))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonById(123L);

    Mockito.verify(personRepository).findById(eq(123L));

    assertEquals(returnedPerson.getId(), person.getId());
    assertEquals(returnedPerson.getUsername(), person.getUsername());
    assertEquals(returnedPerson.getPassword(), person.getPassword());
    assertEquals(returnedPerson.getRole(), person.getRole());
  }

  @Test
  @Description("Gets a person by username.")
  public void testGetPersonByUsername() throws PersonNotFoundException {
    Person person = new Person();
    person.setId(123L);
    person.setUsername("mangusto");
    person.setPassword("123456");
    person.setRole("USER");

    Mockito.when(personRepository.findByUsername(eq("mangusto")))
        .thenReturn(Optional.of(person));

    Person returnedPerson = personService.getPersonByUsername("mangusto");

    Mockito.verify(personRepository).findByUsername(eq("mangusto"));

    assertEquals(returnedPerson.getId(), person.getId());
    assertEquals(returnedPerson.getUsername(), person.getUsername());
    assertEquals(returnedPerson.getPassword(), person.getPassword());
    assertEquals(returnedPerson.getRole(), person.getRole());
  }

  @Test
  @Description("Throws personNotFoundException when username is not found.")
  public void testPersonNotFoundExceptionByUsername() {

    Mockito.when(personRepository.findByUsername(any())).
        thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class,
        () -> personService.getPersonByUsername("notExistingUsername"));
    Mockito.verify(personRepository).findByUsername(eq("notExistingUsername"));
  }

  @Test
  @Description("Throws personNotFoundException when personId is not found.")
  public void testPersonNotFoundExceptionByPersonId() {

    Mockito.when(personRepository.findById(any())).
        thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(9999L));
    Mockito.verify(personRepository).findById(eq(9999L));
  }

  @Test
  @Description("Creates new person.")
  public void testCreatesPerson() {
    Person person = new Person();
    person.setUsername("mangusto");
    person.setPassword("123456");
    person.setRole("USER");

    Person personToBeReturned = new Person();
    personToBeReturned.setId(123L);
    personToBeReturned.setUsername(person.getUsername());
    personToBeReturned.setPassword(person.getPassword());
    personToBeReturned.setRole(person.getRole());

    Mockito.when(personRepository.save(any()))
        .thenReturn(personToBeReturned);

    Person savedPerson = personService.create(person);

    assertEquals(123L, savedPerson.getId());
    assertEquals(personToBeReturned.getUsername(), savedPerson.getUsername());
    assertEquals(personToBeReturned.getPassword(), savedPerson.getPassword());
    assertEquals(personToBeReturned.getRole(), savedPerson.getRole());
  }
}