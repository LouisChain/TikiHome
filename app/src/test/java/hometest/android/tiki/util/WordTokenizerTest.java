package hometest.android.tiki.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WordTokenizerTest {
    @Test
    public void testWordEmpty() {
        String paragraph = null;
        String[] words = WordTokenizer.tokenize(paragraph);
        assertEquals(0, words.length);
    }

    @Test
    public void testWordSeperateWithMoreSpace() {
        String paragraph = "Hell   from   the  other side   ";
        String[] words = WordTokenizer.tokenize(paragraph);
        assertEquals(5, words.length);
        assertEquals("from", words[1]);
    }

    @Test
    public void testWithSpecialCharacters() {
        String paragraph = "Hell \t\t\t  from \n\n\n the, other; side,?   ";
        String[] words = WordTokenizer.tokenize(paragraph);
        assertEquals(5, words.length);
        assertEquals("the", words[2]);
    }

    @Test
    public void testPerfectCase() {
        String paragraph = "Anh chính là thanh xuân của em";
        String[] words = WordTokenizer.tokenize(paragraph);
        assertEquals(7, words.length);
        assertEquals("xuân", words[4]);
    }

    @Test
    public void testBreakIntoTwoLines() {
        String test = "Anh chinh la thanh xuan cua em la la la ??; la   k-al";
        CharSequence actual = WordTokenizer.breakLines(test);
        String[] splt = actual.toString().split("\n");
        assertEquals(2, splt.length);

        String[] token1 = WordTokenizer.tokenize(splt[0]);
        String[] token2 = WordTokenizer.tokenize(splt[1]);
        if (token1.length < token2.length) {
            assertEquals(token1.length + 1, token2.length);
        }
        if (token1.length > token2.length) {
            assertEquals(token1.length, token2.length + 1);
        }
    }
}
