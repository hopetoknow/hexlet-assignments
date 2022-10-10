package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
public class Sorter {

    private static final String GENDER = "gender";
    private static final String MALE_GENDER = "male";
    private static final String BIRTHDAY = "birthday";
    private static final String NAME = "name";

    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        return users.stream()
                .filter(user -> user.get(GENDER).equals(MALE_GENDER))
                .sorted(Comparator.comparing(user -> user.get(BIRTHDAY)))
                .map(user -> user.get(NAME))
                .collect(Collectors.toList());
    }
}
// END
