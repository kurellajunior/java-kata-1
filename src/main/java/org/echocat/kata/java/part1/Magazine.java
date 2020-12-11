package org.echocat.kata.java.part1;

import lombok.Getter;

import java.util.Date;
import java.util.Set;

public class Magazine extends PrintMedia {
  @Getter private final Date publishedAt;

  public Magazine(String title, Set<Author> authors, String isbn, Date publishedAt) {
    super(title, authors, isbn);
    this.publishedAt = publishedAt;
  }
}
