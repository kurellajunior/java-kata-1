package org.echocat.kata.java.part1;

import java.io.PrintStream;
import java.util.Set;

/**
 *
 */
public abstract class PrintMedia {
  protected final String title;
  protected final Set<Author> authors;
  protected final String ISBN;

  public PrintMedia(String title, Set<Author> authors,  String isbn) {
    this.title = title;
    this.authors = authors;
    ISBN = isbn;
  }

  public Set<Author> getAuthors() {
    return authors;
  }

  public abstract void print(PrintStream target);
}
