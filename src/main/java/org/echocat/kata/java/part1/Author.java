package org.echocat.kata.java.part1;

public class Author {
  private final String firstName;
  private final String lastName;
  private final String eMail;

//TODO check validity of e-mail
  public Author(String firstName, String lastName, String eMail) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.eMail = eMail;
  }

  public String getEMail() {
    return eMail;
  }
}
