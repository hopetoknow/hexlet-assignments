package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
public class App {
    public static <T> List<Map<T, T>> findWhere(List<Map<T, T>> books, Map<T, T> where) {
        List<Map<T, T>> result = new ArrayList<>();

        for (Map<T, T> book: books) {
            boolean isFits = true;
            for (T demand: where.keySet()) {
                if (!where.get(demand).equals(book.get(demand))) {
                    isFits = false;
                }
            }

            if (isFits) {
                result.add(book);
            }
        }

        return result;
    }
}
//END
