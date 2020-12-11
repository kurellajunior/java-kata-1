package org.echocat.kata.java.part1;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

public class Magazine extends PrintMedia {
  private final Date publishedAt;

  public Magazine(String title, Set<Author> authors, String isbn, Date publishedAt) {
    super(title, authors, isbn);
    this.publishedAt = publishedAt;
  }

  @Override
  public void print(PrintStream target) {
    target.println(title); // more beauty wanted
    target.println("----");
    target.println("Autor"+((authors.size()) >1 ? "en: " : ": ") + authors.stream().map(Author::getShortDisplay).collect(Collectors.toList()));
    target.println("Veröffentlicht: " + new SimpleDateFormat("dd.MM.yyyy").format(publishedAt));
    target.println("ISBN: " + ISBN);
    target.println();
  }
}
