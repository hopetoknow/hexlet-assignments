package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.stream.Stream;

// BEGIN
public class App {

    private static final String LINE_START = "environment=";
    private static final String VARIABLE_PREFIX = "X_FORWARDED_";

    public static String getForwardedVariables(String content) {
        return content.lines()
                .filter(line -> line.startsWith(LINE_START))
                .map(line -> line.replaceAll(LINE_START, ""))
                .map(line -> line.replaceAll("\"", ""))
                .map(line -> line.split(","))
                .flatMap(Stream::of)
                .filter(elem -> elem.startsWith(VARIABLE_PREFIX))
                .map(elem -> elem.replaceAll(VARIABLE_PREFIX, ""))
                .collect(Collectors.joining(","));
    }
}
//END
