package org.echocat.kata.java.part1;

import java.io.PrintStream;
import java.util.Set;

public class Book extends PrintMedia{
  private final String description;

  public Book(String title, Set<Author> authors, String isbn, String description) {
    super(title, authors, isbn);
    this.description = description;
  }

  @Override
  public void print(PrintStream target) {
    target.println(super.title);
  }
}
