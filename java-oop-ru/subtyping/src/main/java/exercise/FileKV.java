package exercise;

import java.util.HashMap;
import java.util.Map;

import static exercise.Utils.*;

// BEGIN
public class FileKV implements KeyValueStorage {

    private String filepath;

    public FileKV(String pathToFile, Map<String, String> dictionary) {
        this.filepath = pathToFile;
        dictionary.forEach(this::set);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> map = getMapFromFile();
        map.put(key, value);
        writeFile(filepath, serialize(map));
    }

    @Override
    public void unset(String key) {
        Map<String, String> map = getMapFromFile();
        map.remove(key);
        writeFile(filepath, serialize(map));
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> map = getMapFromFile();
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return getMapFromFile();
    }

    private Map<String, String> getMapFromFile() {
        String content = readFile(filepath);
        return unserialize(content);
    }
}
// END
