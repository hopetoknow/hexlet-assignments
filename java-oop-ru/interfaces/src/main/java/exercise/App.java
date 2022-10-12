package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {

    public static List<String> buildAppartmentsList(List<Home> appartments, int elementNumber)  {
        return appartments.stream()
                .sorted(Home::compareTo)
                .limit(elementNumber)
                .map(Object::toString)
                .collect(Collectors.toList());
    }
}
// END
