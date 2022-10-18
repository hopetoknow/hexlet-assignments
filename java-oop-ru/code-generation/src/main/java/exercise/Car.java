package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Value;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(this);
    }

    public static Car unserialize(String json) throws IOException {
        return OBJECT_MAPPER.readValue(json, Car.class);
    }
    // END
}
