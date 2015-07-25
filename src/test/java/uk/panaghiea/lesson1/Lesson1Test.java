package uk.panaghiea.lesson1;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class Lesson1Test {

    private Lesson1 lesson1;
    @Before
    public void setUp() throws Exception {
        lesson1 = new Lesson1();
    }

    @Test
    public void testGetFirstLetters() throws Exception {
        List<String> list = new ArrayList<>(Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot"));

        assertThat(lesson1.getFirstLetters(list), equalTo("abcdef"));
    }

    @Test
    public void testRemoveOddLengthWords() throws Exception {
        List<String> list = new ArrayList<>(Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot"));

        List<String> evenLengthWords = Arrays.asList("echo");
        assertThat(lesson1.removeOddLengthWords(list), equalTo(evenLengthWords));
    }

    @Test
    public void testReplaceWordsWithUpperCase() throws Exception {
        List<String> list = new ArrayList<>(Arrays.asList(
                "alpha", "bravo", "charlie", "delta", "echo", "foxtrot"));

        List<String> upperCaseWords = Arrays.asList("ALPHA", "BRAVO", "CHARLIE", "DELTA", "ECHO", "FOXTROT");
        assertThat(lesson1.replaceWordsWithUpperCase(list), equalTo(upperCaseWords));
    }

    @Test
    public void testConvertMapToString() throws Exception {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 3);
        map.put("b", 2);
        map.put("a", 1);

        assertThat(lesson1.convertMapToString(map), equalTo("a1b2c3"));
    }

    @Test
    public void testPrintNumbersWithThread() throws Exception {
        lesson1.setExecutor(Executors.newFixedThreadPool(1));

        Future<Integer> numbersPrinted = lesson1.printNumbersWithExecutor(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assertThat(numbersPrinted.get(1000, TimeUnit.MILLISECONDS), equalTo(10));
    }
}