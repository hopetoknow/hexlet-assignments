package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// BEGIN
public class App {

    public static String[][] enlargeArrayImage(String[][] image) {
        return Arrays.stream(image)
                .map(line -> Stream.of(line)
                        .flatMap(item -> Stream.of(item, item))
                        .toArray(String[]::new))
                .flatMap(item -> Stream.of(item, item))
                .toArray(String[][]::new);
    }
}
// END
