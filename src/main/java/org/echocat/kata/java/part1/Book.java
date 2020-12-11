package org.echocat.kata.java.part1;

import java.util.Set;

public class Book extends PrintMedia{
  private final String description;

  public Book(String title, Set<Author> authors, String isbn, String description) {
    super(title, authors, isbn);
    this.description = description;
  }
}
