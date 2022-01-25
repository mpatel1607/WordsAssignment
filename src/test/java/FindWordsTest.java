import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class FindWordsTest {

    Logger log = Logger.getLogger(FindWordsTest.class.getName());

    Words word = new Words();
    BufferedReader reader = new BufferedReader(new FileReader("dictionary.txt"));
    BufferedReader readerTwo = new BufferedReader(new FileReader("dictionaryTwo.txt"));
    String WORD_TO_TEST = "Working";

    @Test
    public void doesGivenStringHasActualEnglishWords() throws IOException {
        Assert.assertFalse(WORD_TO_TEST == null, "Given word is null, Please check the input");
        List<String> possibleWordsFromInput = word.findAllPossibleWordsFromInput(reader, readerTwo, WORD_TO_TEST.toLowerCase());
        Assert.assertTrue(!possibleWordsFromInput.isEmpty(), "Given word doesn't have any other word possible from it");
        for (String word: possibleWordsFromInput) {
            log.info("Found possible word: "+ word);
        }
    }

    public FindWordsTest() throws FileNotFoundException {
    }
}
