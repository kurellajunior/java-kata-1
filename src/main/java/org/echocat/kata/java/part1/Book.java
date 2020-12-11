package org.echocat.kata.java.part1;

import java.io.PrintStream;
import java.util.Set;
import java.util.stream.Collectors;

public class Book extends PrintMedia{
  private final String description;

  public Book(String title, Set<Author> authors, String isbn, String description) {
    super(title, authors, isbn);
    this.description = description;
  }

  @Override
  public void print(PrintStream target) {
    target.println(title + " von " + authors.stream().map(Author::getShortDisplay).collect(Collectors.toList())); // more beauty wanted
    target.println("====");
    target.println(description); // TODO nice line breaks?
    target.println("ISBN: " + ISBN);
    target.println();
  }
}
