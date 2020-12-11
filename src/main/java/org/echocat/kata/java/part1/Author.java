package org.echocat.kata.java.part1;

import lombok.Getter;

public class Author {
  @Getter private final String firstName;
  @Getter private final String lastName;
  @Getter private final String eMail;

//TODO check validity of e-mail
  public Author(String firstName, String lastName, String eMail) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.eMail = eMail;
  }
}
