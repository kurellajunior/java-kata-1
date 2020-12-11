package org.echocat.kata.java.part1;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainAppUnitTest {

    @Test
    public void testReadAuthors() {
        final Set<Author> authors = MainApp.readAuthors();
        assertThat(authors, isA(Set.class));
        assertThat(authors.size(), is(equalTo(6)));
    }

    @Test
    public void testReadBooks() {
        final Set<PrintMedia> books = new HashSet<>();
        MainApp.readBooks(books, MainApp.readAuthors());
        assertThat(books, isA(Set.class));
        assertThat(books.size(), is(equalTo(8)));
        assertThat(books.iterator().next().getAuthors().iterator().next(), isA(Author.class));
    }

    @Test
    public void testReadMagazins() {
        final Set<PrintMedia> magazines = new HashSet<>();
        MainApp.readMagazines(magazines, MainApp.readAuthors());
        assertThat(magazines, isA(Set.class));
        assertThat(magazines.size(), is(equalTo(6)));
        assertThat(magazines.iterator().next().getAuthors().iterator().next(), isA(Author.class));
    }

}
