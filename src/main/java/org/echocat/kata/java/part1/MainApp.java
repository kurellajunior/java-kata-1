package org.echocat.kata.java.part1;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class MainApp {

  public static final String SEP = ";";

  public static void main(String[] args) {
    final Set<Author> authors = readAuthors();
    final Set<PrintMedia> library = new HashSet<>();
    readBooks(library, authors);
    readMagazines(library, authors);

    // Output task
    library.forEach(m -> m.print(System.out));

    // task 3
    findByISBN(library, "3214-5698-7412").ifPresent(m -> m.print(System.out));

    // task 4
    findByMail(library, "null-walter@echocat.org").forEach(printMedia -> printMedia.print(System.out));

    // task 5
    library.stream().sorted(Comparator.comparing(printMedia -> printMedia.title)).forEach(printMedia -> printMedia.print(System.out));
  }

  private static Optional<? extends PrintMedia> findByISBN(Set<? extends PrintMedia> library, String isbn) {
    return library.stream().filter(media -> media.ISBN.equals(isbn)).findFirst();
  }

  private static List<? extends PrintMedia> findByMail(Set<? extends PrintMedia> library, String mail) {
    return library.stream().filter(media -> media.authors.stream().anyMatch(author -> author.getEMail().equals(mail))).collect(Collectors.toList());
  }

  protected static void readBooks(Set<PrintMedia> library, Set<Author> authors) {
    try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/books.csv")) {
      if (scanner.hasNextLine())  scanner.nextLine(); // skip header, use later for dynamic column order
      while (scanner.hasNextLine()) parseBook(scanner.nextLine(), authors).ifPresent(library::add);
    }
  }

  protected static void readMagazines(Set<PrintMedia> library, Set<Author> authors) {
    try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/magazines.csv")) {
      if (scanner.hasNextLine())  scanner.nextLine(); // skip header, use later for dynamic column order
      while (scanner.hasNextLine()) parseMagazine(scanner.nextLine(), authors).ifPresent(library::add);
    }
  }

  protected static Set<Author> readAuthors() {
    Set<Author> authors = new HashSet<>();
    try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/authors.csv")) {
      if (scanner.hasNextLine())  scanner.nextLine(); // skip header, use later for dynamic column order
      while (scanner.hasNextLine()) authors.add(parseAuthor(scanner.nextLine()));
    }
    return authors;
  }

  private static Optional<Book> parseBook(String csvLine, Set<Author> authors) {
    final String[] details = csvLine.split(SEP);
    try {
      final Set<Author> authorSet = parseAuthorRefs(authors, details[2]);
      return Optional.of(new Book(details[0], authorSet, details[1], details[3]));
    } catch (NoSuchElementException e) {
      System.err.println("missing author. skipping book »" + details[0] + "«");
      return Optional.empty();
    }
  }

  private static Optional<Magazine> parseMagazine(String csvLine, Set<Author> authors) {
    final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    final String[] details = csvLine.split(SEP);
    try {
      final Set<Author> authorSet = parseAuthorRefs(authors, details[2]);
      return Optional.of(new Magazine(details[0], authorSet, details[1], sdf.parse(details[3])));
    } catch (NoSuchElementException e) {
      System.err.println("missing author. skipping magazine »" + details[0] + "«");
      return Optional.empty();
    } catch (ParseException e) {
      System.err.println("found broken date format, skipping magazine »" + details[0] + "«");
      return Optional.empty();
    }
  }

  /**
   * @param authors known authors
   * @param authorRefString comma separated list of author mails
   * @return lis of authors
   * @throws NoSuchElementException if the author’s mail cannot be mached to the known authors
   */
  private static Set<Author> parseAuthorRefs(Set<Author> authors, String authorRefString) {
    return Arrays.stream(authorRefString.split(","))
        .map((authorMail) -> authors.stream()
            .filter((author -> author.getEMail().equals(authorMail)))
            .findFirst()
            .orElseThrow())
        .collect(Collectors.toSet());
  }

  private static Author parseAuthor(String nextLine) {
    final String[] details = nextLine.split(SEP);
    // TODO evaluate format stability and add safeguards
    return new Author(details[1], details[2], details[0]);
  }

  private static Scanner getScannerFor(String dataSource) {
    return new Scanner(MainApp.class.getResourceAsStream(dataSource), StandardCharsets.UTF_8);
  }

}
