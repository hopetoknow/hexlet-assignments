package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
public class App {
    public static long getCountOfFreeEmails(List<String> emails) {
        String[] freeDomens = {
                "gmail.com",
                "yandex.ru",
                "hotmail.com"
        };
        List<String> freeDomensList = List.of(freeDomens);
        return emails.stream()
                .filter(email -> freeDomensList.stream()
                        .anyMatch(domen -> email.substring(email.indexOf("@")).contains(domen)))
                .count();
    }
}
// END
