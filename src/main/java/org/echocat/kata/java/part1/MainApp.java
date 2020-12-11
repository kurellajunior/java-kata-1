package org.echocat.kata.java.part1;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainApp {

    public static void main(String[] args) {
        final Set<Author> authors = readAuthors();

    }

    protected static Set<Author> readAuthors() {
        Set<Author> authors = new HashSet<>();
        try (Scanner scanner = new Scanner(MainApp.class.getResourceAsStream("/org/echocat/kata/java/part1/data/authors.csv"));) {
            while (scanner.hasNextLine()) {
                authors.add(parseAuthor(scanner.nextLine()));
            }
        }
        return authors;
    }

    private static Author parseAuthor(String nextLine) {
        return null;
    }

}
