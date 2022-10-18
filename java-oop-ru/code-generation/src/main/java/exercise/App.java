package exercise;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

// BEGIN
public class App {

    public static void save(Path path, Car car) {
        String json = car.serialize();
        try {
            Files.writeString(Paths.get(path.toFile().toURI()), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car extract(Path path) {
        String json;
        try {
            json = Files.readAllLines(path).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Car.unserialize(json);
    }
}
// END
