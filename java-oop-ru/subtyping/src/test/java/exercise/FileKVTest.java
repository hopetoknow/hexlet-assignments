package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

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
        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("key", "value"));
        assertThat(storage.get("keykey", "default")).isEqualTo("default");
        assertThat(storage.get("key", "default")).isEqualTo("value");

        storage.set("key42", "value42");
        storage.set("key", "new value");
        assertThat(storage.get("key42", "default")).isEqualTo("value42");
        assertThat(storage.get("key", "default")).isEqualTo("new value");

        storage.unset("key");
        assertThat(storage.get("key", "default")).isEqualTo("default");

        assertThat(storage.toMap()).isEqualTo(Map.of("key42", "value42"));
    }
    // END
}
