package org.echocat.kata.java.part1;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainApp {

    public static void main(String[] args) {
        final Set<Author> authors = readAuthors();
        final Set<? extends PrintMedia> library = readAllMedia();

    }

    private static Set<? extends PrintMedia> readAllMedia() {
        Set<PrintMedia> media = new HashSet<>(readBooks());
        media.addAll(readMagazines());
        return media;
    }

    private static Set<? extends PrintMedia> readBooks() {
        Set<Book> books = new HashSet<>();
        try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/books.csv")) {
            // skip header, use later for dynamic column order
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                books.add(parseBook(scanner.nextLine()));
            }
        }
        return books;
    }

    private static Set<? extends PrintMedia> readMagazines() {
        Set<Magazine> magazines = new HashSet<>();
        try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/magazines.csv")) {
            // skip header, use later for dynamic column order
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                magazines.add(parseMagazine(scanner.nextLine()));
            }
        }
        return magazines;
    }

    protected static Set<Author> readAuthors() {
        Set<Author> authors = new HashSet<>();
        try (Scanner scanner = getScannerFor("/org/echocat/kata/java/part1/data/authors.csv")) {
            // skip header, use later for dynamic column order
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                authors.add(parseAuthor(scanner.nextLine()));
            }
        }
        return authors;
    }

    private static Book parseBook(String csvLine) {
        return null;
    }

    private static Magazine parseMagazine(String csvLine) {
        return null;
    }

    private static Author parseAuthor(String nextLine) {
        final String sep = ";";
        final String[] details = nextLine.split(sep);
        // TODO evaluate format stability and add safeguards
        return new Author(details[1], details[2], details[0]);
    }

    private static Scanner getScannerFor(String dataSource) {
        return new Scanner(MainApp.class.getResourceAsStream(dataSource), StandardCharsets.UTF_8);
    }

}
