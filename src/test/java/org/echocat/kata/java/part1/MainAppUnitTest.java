package org.echocat.kata.java.part1;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.MatcherAssert.assertThat;

public class MainAppUnitTest {

    @Test
    public void testReadAuthors() {
        assertThat(MainApp.readAuthors(), isA(Set.class));
    }

}
