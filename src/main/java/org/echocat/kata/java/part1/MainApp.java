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
    final Set<? extends PrintMedia> library = readAllMedia(authors);

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

  private static Set<? extends PrintMedia> readAllMedia(Set<Author> authors) {
    Set<PrintMedia> media = new HashSet<>(readBooks(authors));
    media.addAll(readMagazines(authors));
    return media;
  }

  protected static Set<? extends PrintMedia> readBooks(Set<Author> authors) {
    Set<Book> books = new HashSet<>();
    try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/books.csv")) {
      // skip header, use later for dynamic column order
      if (scanner.hasNextLine())  scanner.nextLine();
      while (scanner.hasNextLine()) {
        books.add(parseBook(scanner.nextLine(), authors));
      }
    } catch (NoSuchElementException e) {
      System.err.println("missing author in books, input aborted: " + e);
    }
    return books;
  }

  protected static Set<? extends PrintMedia> readMagazines(Set<Author> authors) {
    Set<Magazine> magazines = new HashSet<>();
    try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/magazines.csv")) {
      // skip header, use later for dynamic column order
      if (scanner.hasNextLine())  scanner.nextLine();
      while (scanner.hasNextLine()) {
        magazines.add(parseMagazine(scanner.nextLine(), authors));
      }
    } catch (ParseException e) {
      System.err.println("found broken date format, input aborted: " + e);
    } catch (NoSuchElementException e) {
      System.err.println("missing author in magazines, input aborted: " + e);
    }
    return magazines;
  }

  protected static Set<Author> readAuthors() {
    Set<Author> authors = new HashSet<>();
    try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/authors.csv")) {
      // skip header, use later for dynamic column order
      if (scanner.hasNextLine())  scanner.nextLine();
      while (scanner.hasNextLine()) {
        authors.add(parseAuthor(scanner.nextLine()));
      }
    }
    return authors;
  }

  private static Book parseBook(String csvLine, Set<Author> authors) {
    final String[] details = csvLine.split(SEP);
    // TODO evaluate format stability and add safeguards
    return new Book(details[0],
                    parseAuthorRefs(authors, details[2]),
                    details[1],
                    details[3]);
  }

  private static Magazine parseMagazine(String csvLine, Set<Author> authors) throws ParseException {
    final String[] details = csvLine.split(SEP);
    // TODO evaluate format stability and add safeguards
    return new Magazine(details[0],
                        parseAuthorRefs(authors, details[2]),
                        details[1],
                        new SimpleDateFormat("dd.MM.yyyy").parse(details[3]));
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
