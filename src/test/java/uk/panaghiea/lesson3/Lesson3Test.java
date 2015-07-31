package uk.panaghiea.lesson3;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class Lesson3Test extends TestCase {

    public void testComputeLevenshtein() throws Exception {
        List<String> wordList = Arrays.asList("abc","abd");

        int[][] distances = Lesson3.computeLevenshtein(wordList, false);

        assertThat(distances[0][0],equalTo(0));
        assertThat(distances[0][1],equalTo(distances[1][0]));
        assertThat(distances[0][1],equalTo(1));
    }
}