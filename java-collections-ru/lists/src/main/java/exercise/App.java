package exercise;

import java.util.ArrayList;

// BEGIN
public class App {
    public static boolean scrabble(String letters, String word) {
        ArrayList<Character> wordList = new ArrayList<>(word.length());
        for ( char c : word.toLowerCase().toCharArray() ) {
            wordList.add(c);
        }
        ArrayList<Character> lettersList = new ArrayList<>(letters.length());
        for ( char c : letters.toCharArray() ) {
            lettersList.add(c);
        }

        for (int i = 0; i < wordList.size(); i++) {
            Character tempCh = wordList.get(i);
            if (!lettersList.contains(tempCh)) {
                return false;
            }
            lettersList.remove(tempCh);
        }
        return true;
    }
}
//END
