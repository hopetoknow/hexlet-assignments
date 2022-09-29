package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class App {
    public static Map getWordCount(String sentence) {
        Map<String, Integer> resultMap = new HashMap<>();
        String[] words = sentence.split(" ");

        if (sentence.length() == 0) {
            return resultMap;
        }

        for (String word: words) {
            int wordCount = resultMap.getOrDefault(word, 0);
            wordCount++;
            resultMap.put(word, wordCount);
        }
        return resultMap;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.size() == 0) {
            return "{}";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key: map.keySet()) {
            sb.append("\n")
                    .append("  ")
                    .append(key)
                    .append(": ")
                    .append(map.get(key));
        }
        sb.append("\n}");
        return sb.toString();
    }
}
//END
