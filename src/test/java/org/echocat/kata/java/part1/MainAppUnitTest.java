package org.echocat.kata.java.part1;

import org.junit.Test;

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
        final Set<? extends PrintMedia> books = MainApp.readBooks(MainApp.readAuthors());
        assertThat(books, isA(Set.class));
        assertThat(books.size(), is(equalTo(8)));
    }

    @Test
    public void testReadMagazins() {
        final Set<? extends PrintMedia> magazines = MainApp.readMagazines(MainApp.readAuthors());
        assertThat(magazines, isA(Set.class));
        assertThat(magazines.size(), is(equalTo(6)));
    }

}
