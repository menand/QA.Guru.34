package lesson07.utils;

import java.util.Locale;
import net.datafaker.Faker;

public class RandomUtils {
    Faker faker = new Faker(new Locale("en-GB"));

    public  String findCityByState(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> "";
        };
    }

}
