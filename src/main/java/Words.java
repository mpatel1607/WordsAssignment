import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Words {

    Logger log = Logger.getLogger(Words.class.getName());

    public List<String> findAllPossibleWordsFromInput(BufferedReader firstFile, BufferedReader secondFile, String wordToTest) throws IOException {
        // Declaring some variables at start of the test
        List<Character> listOfCharactersInTestWord = new ArrayList<Character>();
        List<Character> uniqueCharactersInTestWord = new ArrayList<Character>();
        Map<Character, Integer> countOfEachUniqueCharacterInTestWord = new HashMap<Character, Integer>();
        List<String> listOfPossibleWords = new ArrayList<String>();

        char[] chsInTestWord = wordToTest.toCharArray();
        log.info("Adding all characters of given test word in list");
        for (Character ch : chsInTestWord) {
            listOfCharactersInTestWord.add(ch);
        }

        log.info("Get all unique characters from given test word");
        uniqueCharactersInTestWord = getUniqueCharacters(chsInTestWord);

        log.info("Get all unique characters from given test word");
        countOfEachUniqueCharacterInTestWord = getCountOfCharacter(chsInTestWord, uniqueCharactersInTestWord);

        for (String wordFromDic = firstFile.readLine(); wordFromDic != null; wordFromDic = firstFile.readLine()) {
            if (isLengthOfWordExpected(wordFromDic, wordToTest) && isWordContainExpectedCh(wordFromDic, uniqueCharactersInTestWord) && isNumberOfChExpectedInWord(wordFromDic, countOfEachUniqueCharacterInTestWord)) {
                System.out.println("Possible Word From First Dictionary: " + wordFromDic);
                listOfPossibleWords.add(wordFromDic);
            }
        }

        for (String wordFromSecondDic = secondFile.readLine(); wordFromSecondDic != null; wordFromSecondDic = secondFile.readLine()) {
            if (isLengthOfWordExpected(wordFromSecondDic, wordToTest) && isWordContainExpectedCh(wordFromSecondDic, uniqueCharactersInTestWord) && isNumberOfChExpectedInWord(wordFromSecondDic, countOfEachUniqueCharacterInTestWord)) {
                System.out.println("Possible Word From Second Dictionary: " + wordFromSecondDic);
                if (!listOfPossibleWords.contains(wordFromSecondDic)) {
                    listOfPossibleWords.add(wordFromSecondDic);
                }
            }
        }

        return listOfPossibleWords;
    }

    static List<Character> getUniqueCharacters(char[] chArrayOfTestWord) {
        List<Character> listOfCharacters = new ArrayList<Character>();
        for (Character ch : chArrayOfTestWord) {
            if (!listOfCharacters.contains(ch)) {
                listOfCharacters.add(ch);
            }
        }
        return listOfCharacters;
    }

    static Map<Character, Integer> getCountOfCharacter(char[] chArrayOfTestWord, List<Character> uniqueChs) {
        Map <Character, Integer> countOfCharacters = new HashMap<Character, Integer>();
        for (Character chFromList : uniqueChs) {
            countOfCharacters.put(chFromList, 0);
            for (Character chFromArray: chArrayOfTestWord) {
                if (chFromList == chFromArray) {
                    countOfCharacters.put(chFromList, countOfCharacters.get(chFromList) + 1);
                }
            }
        }
        return countOfCharacters;
    }

    static Boolean isLengthOfWordExpected(String wordFromDic, String testWord) {
        if (wordFromDic.length() <= testWord.length()) {
            return true;
        }
        return false;
    }

    static Boolean isWordContainExpectedCh(String wordFromDic, List<Character> chArrayOfTestWord) {
        char[] chOfWord = wordFromDic.toCharArray();
        for (Character ch : chOfWord) {
            if (!chArrayOfTestWord.contains(ch)) {
                return false;
            }
        }
        return true;
    }

    static Boolean isNumberOfChExpectedInWord(String wordFromDic, Map<Character, Integer> mapOfTestWordChs) {
        List<Character> uniqueCharactersInDicWord = getUniqueCharacters(wordFromDic.toCharArray());
        Map <Character, Integer> countOfEachCharacterInDicWord = getCountOfCharacter(wordFromDic.toCharArray(), uniqueCharactersInDicWord);
        for (Map.Entry<Character, Integer> entry : countOfEachCharacterInDicWord.entrySet()) {
            if (entry.getValue() > mapOfTestWordChs.get(entry.getKey())) {
                return false;
            }
        }
        return true;
    }
}
