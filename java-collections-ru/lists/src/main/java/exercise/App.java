package exercise;

import java.util.ArrayList;
import java.util.Arrays;

// BEGIN
public class App {
    public static boolean scrabble(String letters, String word) {
        String[] lettersArr = letters.split("");
        ArrayList<String> lettersList = new ArrayList<>(Arrays.asList(lettersArr));

        String[] wordArr = word.toLowerCase().split("");

        for (int i = 0; i < word.length(); i++) {
            String currentWord = wordArr[i];
            if (!lettersList.contains(wordArr[i])) {
                return false;
            }
            lettersList.remove(currentWord);
        }
        return true;
    }
}
//END
