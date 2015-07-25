package uk.panaghiea.lesson2;

import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class Lesson2Test extends TestCase {

    private Lesson2 lesson2;

    @Override
    public void setUp() throws Exception {
        lesson2 = new Lesson2();
    }

    public void testToLowerCase() throws Exception {
        List<String> list = Arrays.asList("The", "Quick", "BROWN", "fox");

        List<String> result = lesson2.toLowercase(list, s -> true);

        assertThat(result, equalTo(Arrays.asList(
                "the", "quick", "brown", "fox")));
    }

    public void testToLowerCaseWithOddLengthFilter() throws Exception {
        List<String> list = Arrays.asList("Fox", "Jumped", "Over", "The", "LAZY", "DOG");

        List<String> result = lesson2.toLowercase(list, s -> (s.length() & 1) == 1);

        assertThat(result, equalTo(Arrays.asList("fox", "the", "dog")));
    }

    public void testJoinElementsWithHyphen() throws Exception {
        List<String> list = Arrays.asList("The", "quick", "brown", "fox", "jumped", "over", "the");

        String result = lesson2.joinElements(list, 2, 4, "-");

        assertThat(result, equalTo("brown-fox-jumped-over"));
    }

    public void testCountNumberOfLines() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader("AAA \n BBB"));

        Long numberOfLines = lesson2.countNumberOfLines(reader);

        assertThat(numberOfLines,equalTo(2L));
    }

    public void testListOfWords() throws Exception {
        BufferedReader reader = new BufferedReader(new StringReader("AAA aaa\nAAA bbb aaa BBB"));

        List<String> words = lesson2.createListOfDistinctWords(reader);

        assertThat(words, equalTo(Arrays.asList("AAA","aaa", "bbb","BBB")));
    }

    public void testlistOfWordsIgnoreCase() throws Exception {
        BufferedReader reader =  new BufferedReader(new StringReader(
                "AAA aaa bbb\n" +
                "BBB aAa bBB"));

        List<String> words = lesson2.createListOfWordsIgnoreCase(reader, String::compareTo);

        assertThat(words, equalTo(Arrays.asList("aaa", "bbb")));
    }

    public void testlistOfWordsIgnoreCaseSortedByLength() throws Exception {
        BufferedReader reader =  new BufferedReader(new StringReader(
                "AAAA aaa a bbbb\n" +
                "BBBB Bb aAa bBB"));

        List<String> words = lesson2.createListOfWordsIgnoreCase(reader, (o1, o2) -> o1.length() - o2.length());

        assertThat(words, equalTo(Arrays.asList("a", "bb", "aaa", "bbb","aaaa","bbbb")));
    }
}