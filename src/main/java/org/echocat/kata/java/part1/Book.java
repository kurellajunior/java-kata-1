package org.echocat.kata.java.part1;

import lombok.Getter;

import java.util.Set;

public class Book extends PrintMedia{
  @Getter private final String description;

  public Book(String title, Set<Author> authors, String isbn, String description) {
    super(title, authors, isbn);
    this.description = description;
  }
}
