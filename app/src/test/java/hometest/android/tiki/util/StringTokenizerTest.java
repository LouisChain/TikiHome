package hometest.android.tiki.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringTokenizerTest {
    @Test
    public void testWordEmpty() {
        String paragraph = null;
        String [] words = WordTokenizer.tokenize(paragraph);
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
}
