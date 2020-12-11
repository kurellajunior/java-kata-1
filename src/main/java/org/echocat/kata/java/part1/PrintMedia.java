package org.echocat.kata.java.part1;

import lombok.Getter;

import java.util.Set;

/**
 *
 */
public class PrintMedia {
  @Getter private final String title;
  @Getter private final Set<Author> authors;
  @Getter private final String ISBN;

  public PrintMedia(String title, Set<Author> authors,  String isbn) {
    this.title = title;
    this.authors = authors;
    ISBN = isbn;
  }
}
