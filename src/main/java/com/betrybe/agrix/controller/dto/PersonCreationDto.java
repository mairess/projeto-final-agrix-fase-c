package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.entity.Person;
import com.betrybe.agrix.security.Role;

public record PersonCreationDto(String username, String password, Role role) {

  public Person toEntity() {
    return new Person(username, password, role);
  }
}