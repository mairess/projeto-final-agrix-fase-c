package com.betrybe.agrix.service.exception;

/**
 * Exception for when a person is not found.
 */
public class PersonNotFoundException extends NotFoundException {

  public PersonNotFoundException() {
    super("Pessoa não encontrada!");
  }

}