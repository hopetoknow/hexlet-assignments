package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class Validator {

    public static List<String> validate(Address address) {
        List<String> invalidProperties = new ArrayList<>();
        Field[] fields = address.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getAnnotation(NotNull.class) != null && field.get(address) == null) {
                    invalidProperties.add(field.getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return invalidProperties;
    }
}
// END
