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

}
