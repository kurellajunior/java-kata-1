package org.echocat.kata.java.part1;

import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainApp {

    public static void main(String[] args) {
        final Set<Author> authors = readAuthors();
        final Set<PrintMedia> library = readAllMedia();

    }

    private static Set<PrintMedia> readAllMedia() {
        Set<PrintMedia> media = readBooks();
        media.addAll(readMagazines());
        return media;
    }

    private static Set<? extends PrintMedia> readMagazines() {
        return new HashSet<>();
    }

    private static Set<PrintMedia> readBooks() {
        return new HashSet<>();
    }

    protected static Set<Author> readAuthors() {
        Set<Author> authors = new HashSet<>();
        try (Scanner scanner = new Scanner(MainApp.class.getResourceAsStream("/org/echocat/kata/java/part1/data/authors.csv"), StandardCharsets.UTF_8)) {
            // skip header, use later for dynamic column order
            if (scanner.hasNextLine()) scanner.nextLine();
            while (scanner.hasNextLine()) {
                authors.add(parseAuthor(scanner.nextLine()));
            }
        }
        return authors;
    }

    private static Author parseAuthor(String nextLine) {
        final String sep = ";";
        final String[] details = nextLine.split(sep);
        // TODO evaluate format stability and add safeguards
        return new Author(details[1], details[2], details[0]);
    }

}
