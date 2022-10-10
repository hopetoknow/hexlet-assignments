package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .flatMap(line -> Stream.of(line, line)
                        .map(element -> Stream.of(element)
                                .flatMap(symbol -> Stream.of(symbol, symbol))
                                .toArray(String[]::new)))
                .toArray(String[][]::new);
    }
}
// END
