package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;

// BEGIN
@AllArgsConstructor
@Getter
// END
class Car {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    public String serialize() {
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Car unserialize(String json) {
        try {
            return objectMapper.readValue(json, Car.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        User owner = new User(1, "Ivan", "Petrov", 25);
        Car car = new Car(1, "bmv", "x5", "black", owner);
        System.out.println(car.serialize());
        String json = car.serialize();
        System.out.println(car.unserialize(json));
        Car car2 = car.unserialize(json);
        System.out.println(car2.getBrand());
    }
    // END
}
