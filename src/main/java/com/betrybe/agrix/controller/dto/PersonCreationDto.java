package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;

/**
 * The type Person creation dto.
 */
public record PersonCreationDto(String username, String password, String role) {

  /**
   * To entity person.
   *
   * @return the person
   */
  public Person toEntity() {
    return new Person(null, username, password, role);
  }
}