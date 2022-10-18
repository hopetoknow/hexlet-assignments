package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class Validator {

    private static final String NULL_ERROR = "can not be null";
    private static final String LENGTH_ERROR = "length less than 3";

    public static List<String> validate(Address address) {
        List<Field> fields = List.of(address.getClass().getDeclaredFields());
        return fields.stream()
                .filter(field -> field.isAnnotationPresent(NotNull.class))
                .filter(field -> {
                    Object value;
                    try {
                        field.setAccessible(true);
                        value = field.get(address);
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return value == null;
                })
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Map<String, List<String>> notValidFields = new HashMap<>();
        List<Field> fields = List.of(address.getClass().getDeclaredFields());
        fields.stream()
                .filter(field -> field.isAnnotationPresent(NotNull.class) || field.isAnnotationPresent(MinLength.class))
                .forEach(field -> {
                    String fieldName = field.getName();
                    List<String> errors = getErrors(field, address);
                    if (!errors.isEmpty()) {
                        notValidFields.put(fieldName, errors);
                    }
                });
        return notValidFields;
    }

    private static List<String> getErrors(Field field, Address address) {
        List<String> errors = new ArrayList<>();
        String value;

        try {
            field.setAccessible(true);
            value = (String) field.get(address);
            field.setAccessible(false);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        if (field.isAnnotationPresent(NotNull.class) && value == null) {
            errors.add(NULL_ERROR);
        }

        if (field.isAnnotationPresent(MinLength.class)) {
            int minLength = field.getAnnotation(MinLength.class).minLength();
            if (value == null || value.length() < minLength) {
                errors.add(LENGTH_ERROR);
            }
        }
        return errors;
    }
}
// END
