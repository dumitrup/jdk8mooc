package uk.panaghiea.lesson3;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.*;

public class RandomWordsTest {
    private RandomWords randomWords;

    @Before
    public void setUp() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader("Word1\nWord2\nWord3"));
        randomWords = new RandomWords(reader);
    }

    @Test
    public void testConstructor() throws Exception {
        assertThat(randomWords.allWords(), hasItems("Word1", "Word2", "Word3"));
    }

    @Test
    public void testCreateList() throws Exception {
        List<String> randomList = randomWords.createList(2);

        assertThat(randomList.size(), equalTo(2));
    }

    @Test
    public void testAllWords() throws Exception {
        assertThat(randomWords.allWords(),hasItems("Word1", "Word2", "Word3"));
    }
}