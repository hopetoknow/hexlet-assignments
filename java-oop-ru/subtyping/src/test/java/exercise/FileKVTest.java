package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static exercise.Utils.*;
import static org.assertj.core.api.Assertions.assertThat;
// BEGIN

// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void fileKVTest() {
        String pathAsString = filepath.toString();
        KeyValueStorage storage = new FileKV(pathAsString, Map.of("key", "10"));
        String storageAsString = serialize(storage.toMap());
        writeFile(pathAsString, storageAsString);
        String content = readFile(pathAsString);
        assertThat(storage.toMap()).isEqualTo(unserialize(content));
    }
    // END
}
