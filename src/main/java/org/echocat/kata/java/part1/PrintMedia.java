package org.echocat.kata.java.part1;

import java.util.Set;

/**
 *
 */
public class PrintMedia {
  private final String title;
  private final Set<Author> authors;
  private final String ISBN;

  public PrintMedia(String title, Set<Author> authors,  String isbn) {
    this.title = title;
    this.authors = authors;
    ISBN = isbn;
  }
}
